// JavaLLMClient.java
package step2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;

import java.io.IOException;

public class JavaLLMClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
    private static final String DEPLOYMENT = System.getenv("AZURE_OPENAI_DEPLOYMENT");
    private static final String API_KEY = System.getenv("AZURE_OPENAI_KEY");
    private static final String API_VERSION = "2025-01-01-preview";

    private static final OkHttpClient client = new OkHttpClient();

    public static String chatComplete(String userPrompt, int maxTokens) throws IOException {
        ObjectNode message = mapper.createObjectNode();
        message.put("role", "user");
        message.put("content", userPrompt);

        ArrayNode messages = mapper.createArrayNode();
        messages.add(message);

        ObjectNode payload = mapper.createObjectNode();
        payload.set("messages", messages);
        payload.put("temperature", 0.7);
        payload.put("max_tokens", maxTokens);

        String url = String.format("%s/openai/deployments/%s/chat/completions?api-version=%s",
                ENDPOINT, DEPLOYMENT, API_VERSION);
        System.out.println("🔗 Sending request to: " + url);

        int retries = 3;
        int[] backoffs = {5000, 10000, 20000};

        for (int attempt = 0; attempt <= retries; attempt++) {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("api-key", API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(payload.toString(), MediaType.get("application/json")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 429 && attempt < retries) {
                    System.out.println("⚠️ Rate limit hit. Retrying in " + backoffs[attempt]/1000 + "s (attempt " + (attempt + 1) + ")");
                    try { Thread.sleep(backoffs[attempt]); } catch (InterruptedException ignored) {}
                    continue;
                }

                if (!response.isSuccessful()) {
                    System.err.println("❌ Azure LLM failed: " + response.code() + " - " + response.message());
                    return null;
                }

                JsonNode json = mapper.readTree(response.body().string());
                return json.get("choices").get(0).get("message").get("content").asText().trim();
            }
        }

        System.err.println("❌ Failed after retries.");
        return null;
    }
}
