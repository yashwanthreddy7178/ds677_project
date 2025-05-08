package step2;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class InstructionToCode {

    public static void main(String[] args) throws IOException {
        File input = new File("step2_c_i.jsonl");
        File output = new File("step2_i_r.jsonl");

        ObjectMapper mapper = new ObjectMapper();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String instruction = obj.get("instruction").asText();

                String prompt = "Write a Java method based on this instruction:\n" + instruction;

                try {
                    String response = JavaLLMClient.chatComplete(prompt, 256);
                    if (response != null) {
                        ObjectNode out = mapper.createObjectNode();
                        out.put("instruction", instruction);
                        out.put("response", response);
                        writer.write(out.toString());
                        writer.newLine();
                        count++;
                    }
                } catch (Exception e) {
                    System.err.println("⚠️ Skipping due to LLM error: " + e.getMessage());
                }
            }
        }

        System.out.println("✅ step2_i_r.jsonl created: " + count + " items.");
    }
}
