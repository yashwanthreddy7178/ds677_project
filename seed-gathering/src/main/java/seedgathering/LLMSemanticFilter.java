// LLMSemanticFilter.java
package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.SequenceWriter;
import step2.JavaLLMClient;
import step2.JavaLLMClient.Provider;

import java.io.*;
import java.util.HashSet;

public class LLMSemanticFilter {

    public static void main(String[] args) throws IOException, InterruptedException {
        String inputPath = "seeds/typecheck_seeds_loose.jsonl";
        String outputPath = "seeds/llm_verified_seeds_loose.jsonl";

        ObjectMapper mapper = new ObjectMapper();

        int batchSize = 20;
        int requestCount = 0;
        long lastReset = System.currentTimeMillis();

        HashSet<String> seenDocs = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             SequenceWriter writer = mapper.writer()
                 .withRootValueSeparator("\n")
                 .writeValues(new File(outputPath))) {

            int total = 0, kept = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                total++;
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String content = obj.has("content") ? obj.get("content").asText() : obj.get("seed").asText();
                String doc = extractDoc(content);

                if (doc.isBlank() || seenDocs.contains(doc) || isJavadocTagOnly(doc)) {
                    continue;
                }
                seenDocs.add(doc);

                String prompt = buildPrompt(doc);
                String response = "";
                int attempts = 0;

                while (attempts < 5) {
                    try {
                        response = JavaLLMClient.chatComplete(prompt, 10, Provider.AZURE);
                        break;
                    } catch (IOException e) {
                        if (e.getMessage().contains("429")) {
                            long backoff = (long) Math.pow(2, attempts) * 1000;
                            System.out.println("⚠️ Rate limit hit. Retrying in " + (backoff / 1000) + "s...");
                            Thread.sleep(backoff);
                        } else {
                            throw e;
                        }
                    }
                    attempts++;
                }

                if (response != null && response.trim().toLowerCase().startsWith("yes")) {
                    writer.write(obj);
                    writer.flush(); // ensure it's written immediately
                    System.out.println("✅ Accepted: " + response);
                    kept++;
                } else {
                    System.out.println("❌ Rejected: " + response);
                }

                requestCount++;
                if (requestCount >= batchSize) {
                    long elapsed = System.currentTimeMillis() - lastReset;
                    if (elapsed < 6000) {
                        long sleepTime = 6000 - elapsed;
                        System.out.println("⏳ Sleeping for " + (sleepTime / 1000) + "s...");
                        Thread.sleep(sleepTime);
                    }
                    requestCount = 0;
                    lastReset = System.currentTimeMillis();
                }
            }
            System.out.println("✅ Substep 3 Completed: " + kept + " / " + total + " unique docs kept");
        }
    }

    private static String extractDoc(String content) {
        int start = content.indexOf("/**");
        int end = content.indexOf("*/", start);
        if (start == -1 || end == -1 || end <= start + 3)
            return "";
        return content.substring(start + 3, end).trim();
    }

    /**
     * Returns true if any line in the doc (after trimming leading '*' and whitespace) starts with '@'.
     */
    private static boolean isJavadocTagOnly(String doc) {
        String[] lines = doc.split("\n");
        for (String line : lines) {
            String trimmed = line.replaceFirst("^\\s*\\*\\s*", "").trim();
            if (trimmed.startsWith("@")) {
                return true;
            }
        }
        return false;
    }

    private static String buildPrompt(String doc) {
        // Sanitize the Javadoc: remove backticks, excessive whitespace, and limit length
        String cleanDoc = doc.replace('`', '\'')
                             .replaceAll("\\s+", " ")
                             .trim();
        int maxLen = 500; // limit to 500 chars for safety
        if (cleanDoc.length() > maxLen) {
            cleanDoc = cleanDoc.substring(0, maxLen) + "...";
        }
        return "Based only on the following Javadoc, is it clear and informative enough to help someone understand or implement the corresponding Java method? Please answer YES or NO.\n"
                + "Javadoc:\n" + cleanDoc + "\n";
    }
}
