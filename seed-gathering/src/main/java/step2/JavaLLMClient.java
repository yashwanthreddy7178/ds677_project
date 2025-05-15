package step2;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JavaLLMClient {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final OkHttpClient client = new OkHttpClient();
    private static final Dotenv dotenv = Dotenv.load();

    // Azure OpenAI Config
    private static final String AZURE_ENDPOINT = dotenv.get("AZURE_OPENAI_ENDPOINT");
    private static final String AZURE_DEPLOYMENT = dotenv.get("AZURE_OPENAI_DEPLOYMENT");
    private static final String AZURE_KEY = dotenv.get("AZURE_OPENAI_KEY");
    private static final String AZURE_VERSION = "2025-01-01-preview";

    // Together AI Config
    private static final String TOGETHER_KEY = dotenv.get("TOGETHER_API_KEY");
    private static final String TOGETHER_MODEL = dotenv.get("TOGETHER_MODEL");

    public enum Provider {
        TOGETHER,
        AZURE
    }

    public static String chatComplete(String userPrompt, int maxTokens) throws IOException {
        return chatComplete(userPrompt, maxTokens, Provider.AZURE); // default to Azure
    }

    public static String chatComplete(String userPrompt, int maxTokens, Provider provider) throws IOException {
        ObjectNode message = mapper.createObjectNode();
        message.put("role", "user");
        message.put("content", userPrompt);

        ArrayNode messages = mapper.createArrayNode();
        messages.add(message);

        ObjectNode payload = mapper.createObjectNode();
        payload.set("messages", messages);
        payload.put("temperature", 0.7);
        payload.put("max_tokens", maxTokens);

        if (provider == Provider.TOGETHER) {
            if (TOGETHER_KEY != null && TOGETHER_MODEL != null) {
                payload.put("model", TOGETHER_MODEL);
                return callTogether(payload);
            } else {
                throw new IllegalStateException("TOGETHER_AI credentials missing.");
            }
        } else if (provider == Provider.AZURE) {
            if (AZURE_KEY != null && AZURE_ENDPOINT != null && AZURE_DEPLOYMENT != null) {
                return callAzure(payload);
            } else {
                throw new IllegalStateException("AZURE_OPENAI credentials missing.");
            }
        } else {
            throw new IllegalArgumentException("Unknown provider: " + provider);
        }
    }

    private static String callTogether(ObjectNode payload) throws IOException {
        String url = "https://api.together.xyz/v1/chat/completions";
        System.out.println("🔗 Sending request to: " + url);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + TOGETHER_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(payload.toString(), MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("❌ Together AI failed: " + response.code() + " - " + response.message());
                return null;
            }
            JsonNode json = mapper.readTree(response.body().string());
            return json.get("choices").get(0).get("message").get("content").asText().trim();
        }
    }

    private static String callAzure(ObjectNode payload) throws IOException {
        String url = String.format("%s/openai/deployments/%s/chat/completions?api-version=%s",
                AZURE_ENDPOINT, AZURE_DEPLOYMENT, AZURE_VERSION);
        System.out.println("🔗 Sending request to: " + url);

        int retries = 3;
        int[] backoffs = {5000, 10000, 20000};

        for (int attempt = 0; attempt <= retries; attempt++) {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("api-key", AZURE_KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(payload.toString(), MediaType.get("application/json")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 429 && attempt < retries) {
                    System.out.println("⚠️ Rate limit hit. Retrying in " + backoffs[attempt] / 1000 + "s (attempt " + (attempt + 1) + ")");
                    try {
                        Thread.sleep(backoffs[attempt]);
                    } catch (InterruptedException ignored) {
                    }
                    continue;
                }

                if (!response.isSuccessful()) {
                    throw new IOException(response.code() + " " + response.message());
                }

                JsonNode json = mapper.readTree(response.body().string());
                return json.get("choices").get(0).get("message").get("content").asText().trim();
            } catch (IOException e) {
                if (e.getMessage().contains("400")) {
                    System.err.println("❌ 400 Error! Prompt was:\n" + payload.toString());
                }
                throw e;
            }
        }

        System.err.println("❌ Failed after retries.");
        return null;
    }
}
