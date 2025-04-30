// LLMSemanticFilter.java
package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.SequenceWriter;
import okhttp3.*;

import java.io.*;
import java.util.*;

public class LLMSemanticFilter {

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        String inputPath = "seeds/typecheck_seeds.jsonl";
        String outputPath = "seeds/llm_verified_seeds.jsonl";

        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             SequenceWriter writer = mapper.writer().writeValues(new File(outputPath))) {

            int total = 0, kept = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                total++;
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String content = obj.get("content").asText();

                if (askLLM(content)) {
                    writer.write(obj);
                    kept++;
                }
            }
            System.out.println("✅ Substep 3 Completed: " + kept + " / " + total + " kept");
        }
    }

    private static boolean askLLM(String content) throws IOException {
        String prompt = buildPrompt(content);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode message = mapper.createObjectNode();
        message.put("role", "user");
        message.put("content", prompt);

        ObjectNode payload = mapper.createObjectNode();
        payload.put("model", "gpt-3.5-turbo");
        payload.set("messages", mapper.createArrayNode().add(message));
        payload.put("temperature", 0);
        payload.put("max_tokens", 10);

        RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(OPENAI_ENDPOINT)
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) return false;
            JsonNode json = mapper.readTree(response.body().string());
            String reply = json.get("choices").get(0).get("message").get("content").asText().toLowerCase();
            return reply.contains("yes") && !reply.contains("no");
        }
    }

    private static String buildPrompt(String content) {
        String doc = extractDoc(content);
        String code = extractCode(content);

        return "I have a function in Java and I'd like someone to check my description.\n"
             + "Here is the code:\n" + code + "\n"
             + "Here is the description:\n" + doc + "\n"
             + "Answer with \"Yes\" or \"No\" depending on whether the description alone is sufficient to re-implement the function.";
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
