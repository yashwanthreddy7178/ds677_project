package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;

import java.io.*;
import java.util.*;

public class SelfCompletionGen {

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String OPENAI_ENDPOINT = System.getenv("OPENAI_BASE_URL") + "/chat/completions";
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        String inputPath = "seeds/final_seeds.jsonl";
        String outputPath = "generated/s2c.jsonl";
        new File("generated").mkdirs();

        ObjectMapper mapper = new ObjectMapper();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JsonNode node = mapper.readTree(line);
                if (node == null || !node.has("seed")) continue;

                String content = node.get("seed").asText();
                String prompt = buildPrompt(content);
                String completion = callOpenAI(prompt);
                if (completion == null || completion.isBlank()) continue;

                ObjectNode outputNode = mapper.createObjectNode();
                outputNode.put("seed", content);
                outputNode.put("completion", completion);
                writer.write(mapper.writeValueAsString(outputNode));
                writer.newLine();
                count++;
                System.out.println("✅ Generated: " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

        System.out.println("✅ S→C Completed. Total: " + count);
    }

    private static String buildPrompt(String content) {
        int docStart = content.indexOf("/**");
        int docEnd = content.indexOf("*/") + 2;
        String doc = content.substring(docStart, docEnd);
        String codeStart = content.substring(docEnd).replaceAll("\\s+$", "");
        int bodyStart = codeStart.indexOf("{");
        if (bodyStart != -1) codeStart = codeStart.substring(0, bodyStart + 1);

        return "Complete the following Java function based on the given JavaDoc.\n\nJavaDoc:\n"
                + doc.trim() + "\n\nCode:\n" + codeStart.trim();
    }

    private static String callOpenAI(String prompt) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<ObjectNode> messages = new ArrayList<>();
            ObjectNode message = mapper.createObjectNode();
            message.put("role", "user");
            message.put("content", prompt);
            messages.add(message);

            ObjectNode payload = mapper.createObjectNode();
            payload.put("model", "gpt-3.5-turbo");
            payload.set("messages", mapper.valueToTree(messages));
            payload.put("temperature", 0.3);
            payload.put("max_tokens", 256);

            RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(OPENAI_ENDPOINT)
                    .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    System.err.println("⚠️ API Error: " + response.code() + " - " + response.message());
                    return null;
                }

                JsonNode res = mapper.readTree(response.body().string());
                return res.get("choices").get(0).get("message").get("content").asText().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}