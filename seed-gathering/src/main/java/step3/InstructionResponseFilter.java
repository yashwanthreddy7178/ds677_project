// src/step3/InstructionResponseFilter.java
package step3;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import step2.JavaLLMClient;

import java.io.*;
import java.util.*;

public class InstructionResponseFilter {

    private static final int BATCH_SIZE = 5;
    private static final long SLEEP_MS = 6000;

    public static void main(String[] args) throws IOException, InterruptedException {
        File input = new File("seeds/step2_i_r.jsonl");
        File output = new File("seeds/step3_verified.jsonl");

        ObjectMapper mapper = new ObjectMapper();
        int total = 0, kept = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            List<ObjectNode> batch = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                batch.add(obj);

                if (batch.size() >= BATCH_SIZE) {
                    kept += processBatch(batch, writer, mapper);
                    total += batch.size();
                    batch.clear();
                    Thread.sleep(SLEEP_MS);
                }
            }

            if (!batch.isEmpty()) {
                kept += processBatch(batch, writer, mapper);
                total += batch.size();
            }
        }

        System.out.println("✅ Step 3 complete. " + kept + " / " + total + " responses verified and saved to step3_verified.jsonl");
    }

    private static int processBatch(List<ObjectNode> batch, BufferedWriter writer, ObjectMapper mapper) {
        int kept = 0;
        for (ObjectNode obj : batch) {
            String instruction = obj.get("instruction").asText();
            String response = obj.get("response").asText();

            String prompt = "Instruction: " + instruction + "\nResponse: " + response +
                    "\n\nIs this response a correct and complete Java implementation of the instruction? Answer only with 'Yes' or 'No'.";

            try {
                String answer = JavaLLMClient.chatComplete(prompt, 10, JavaLLMClient.Provider.AZURE);
                if (answer != null && answer.toLowerCase().startsWith("yes")) {
                    writer.write(obj.toString());
                    writer.newLine();
                    writer.flush();
                    kept++;
                }
            } catch (Exception e) {
                System.err.println("⚠️ LLM validation failed: " + e.getMessage());
            }
        }
        return kept;
    }
} 
