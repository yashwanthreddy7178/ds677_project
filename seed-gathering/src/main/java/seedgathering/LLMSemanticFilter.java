package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import okhttp3.*;

import java.io.*;
import java.util.*;

public class LLMSemanticFilter {

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final OkHttpClient client = new OkHttpClient();
    private static final int BATCH_SIZE = 50;

    public static void main(String[] args) throws IOException {
        String inputPath = "seeds/typecheck_seeds.jsonl";
        String outputPath = "seeds/llm_verified_seeds.jsonl";

        ObjectMapper mapper = new ObjectMapper();
        List<ObjectNode> buffer = new ArrayList<>();

        int total = 0, kept = 0, apiCalls = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                JsonNode node = mapper.readTree(line);
                if (node == null || node.isMissingNode() || !node.isObject()) continue;
                buffer.add((ObjectNode) node);
                total++;

                if (buffer.size() >= BATCH_SIZE) {
                    List<ObjectNode> verified = processBatch(buffer, mapper);
                    for (ObjectNode obj : verified) {
                        writer.write(mapper.writeValueAsString(obj));
                        writer.newLine();
                        kept++;
                    }
                    apiCalls++;
                    buffer.clear();
                }
            }

            if (!buffer.isEmpty()) {
                List<ObjectNode> verified = processBatch(buffer, mapper);
                for (ObjectNode obj : verified) {
                    writer.write(mapper.writeValueAsString(obj));
                    writer.newLine();
                    kept++;
                }
                apiCalls++;
            }
        }

        System.out.println("✅ Substep 3 Completed: " + kept + " / " + total + " kept");
        System.out.println("📊 Total OpenAI requests made: " + apiCalls);

        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    private static List<ObjectNode> processBatch(List<ObjectNode> batch, ObjectMapper mapper) {
        List<ObjectNode> kept = new ArrayList<>();
        StringBuilder promptBuilder = new StringBuilder();

        for (int i = 0; i < batch.size(); i++) {
            ObjectNode obj = batch.get(i);
            String content = obj.get("content").asText();
            String doc = extractDoc(content);
            String code = extractCode(content);
            promptBuilder.append("Function ").append(i + 1).append(":\n")
                    .append("JavaDoc:\n").append(doc).append("\n")
                    .append("Code:\n").append(code).append("\n\n");
        }

        String prompt = "You will be given several Java functions. For each, answer only YES or NO depending on whether the description alone is sufficient to re-implement the function. Reply as '1. YES', '2. NO', etc.\n\n"
                + promptBuilder.toString();

        try {
            ArrayNode messages = mapper.createArrayNode();
            ObjectNode userMessage = mapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);

            ObjectNode payload = mapper.createObjectNode();
            payload.put("model", "gpt-3.5-turbo");
            payload.set("messages", messages);
            payload.put("temperature", 0);
            payload.put("max_tokens", 50);

            RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(OPENAI_ENDPOINT)
                    .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                    .post(body)
                    .build();

            for (int retry = 0; retry < 5; retry++) {
                try (Response response = client.newCall(request).execute()) {
                    if (response.code() == 429) {
                        System.err.println("⏳ Rate limit hit, retrying in 5s...");
                        Thread.sleep(5000);
                        continue;
                    }
                    if (!response.isSuccessful()) {
                        System.err.println("⚠️ LLM response failed: " + response.code() + " - " + response.message());
                        return kept;
                    }

                    String reply = mapper.readTree(response.body().string())
                            .get("choices").get(0).get("message").get("content").asText().toLowerCase();

                    String[] lines = reply.split("\n");
                    for (int i = 0; i < Math.min(lines.length, batch.size()); i++) {
                        if (lines[i].contains("yes") && !lines[i].contains("no")) {
                            kept.add(batch.get(i));
                        }
                    }

                    Thread.sleep(2000); // Throttle to reduce chance of 429
                    break;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return kept;
                }
            }
        } catch (IOException e) {
            System.err.println("❌ API error: " + e.getMessage());
        }

        return kept;
    }

    private static String extractDoc(String content) {
        int start = content.indexOf("/**");
        int end = content.indexOf("*/", start);
        if (start == -1 || end == -1) return "";
        return content.substring(start + 3, end).trim();
    }

    private static String extractCode(String content) {
        int end = content.indexOf("*/");
        return (end != -1) ? content.substring(end + 2).trim() : content;
    }
}