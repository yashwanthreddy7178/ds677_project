// src/step2/InstructionToCode.java
package step2;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.*;

public class InstructionToCode {

    private static final int BATCH_SIZE = 10;
    private static final long SLEEP_MS = 3000;

    public static void main(String[] args) throws IOException, InterruptedException {
        File input = new File("seeds/step2_c_i.jsonl");
        File output = new File("seeds/step2_i_r.jsonl");

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

        System.out.println("✅ step2_i_r.jsonl created: " + total + " items.");
    }

    private static void processBatch(List<ObjectNode> batch, BufferedWriter writer, ObjectMapper mapper) {
        for (ObjectNode obj : batch) {
            String instruction = obj.get("instruction").asText();
            String prompt = "Write a Java method based on this instruction:\n" + instruction;

            try {
                String response = JavaLLMClient.chatComplete(prompt, 256, JavaLLMClient.Provider.TOGETHER);
                if (response != null) {
                    ObjectNode out = mapper.createObjectNode();
                    out.put("instruction", instruction);
                    out.put("response", response);
                    writer.write(out.toString());
                    writer.newLine();
                    writer.flush();
                }
            } catch (Exception e) {
                System.err.println("⚠️ Skipping due to LLM error: " + e.getMessage());
            }
        }
    }
} 
