// src/step2/CodeToInstruction.java
package step2;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.*;

public class CodeToInstruction {

    private static final int BATCH_SIZE = 5; // Grouped requests
    private static final long SLEEP_MS = 6000; // Delay after each batch

    public static void main(String[] args) throws IOException, InterruptedException {
        File input = new File("seeds/step2_s_c.jsonl");
        File output = new File("seeds/step2_c_i.jsonl");

        ObjectMapper mapper = new ObjectMapper();
        int total = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            List<ObjectNode> batch = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                batch.add(obj);

                if (batch.size() >= BATCH_SIZE) {
                    processBatch(batch, writer, mapper);
                    total += batch.size();
                    batch.clear();
                    Thread.sleep(SLEEP_MS);
                }
            }

            if (!batch.isEmpty()) {
                processBatch(batch, writer, mapper);
                total += batch.size();
            }
        }

        System.out.println("✅ step2_c_i.jsonl created: " + total + " items.");
    }

    private static void processBatch(List<ObjectNode> batch, BufferedWriter writer, ObjectMapper mapper) {
        for (ObjectNode obj : batch) {
            String code = obj.get("canonical_solution").asText();
            String prompt = "Write an instruction describing this Java method:\n" + code;

            try {
                String response = JavaLLMClient.chatComplete(prompt, 64);
                if (response != null) {
                    obj.put("instruction", response);
                    writer.write(obj.toString());
                    writer.newLine();
                    writer.flush();
                }
            } catch (Exception e) {
                System.err.println("⚠️ Skipping due to LLM error: " + e.getMessage());
            }
        }
    }
}
