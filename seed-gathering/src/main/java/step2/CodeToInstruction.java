package step2;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class CodeToInstruction {

    public static void main(String[] args) throws IOException {
        File input = new File("step2_s_c.jsonl");
        File output = new File("step2_c_i.jsonl");

        ObjectMapper mapper = new ObjectMapper();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String code = obj.get("canonical_solution").asText();

                String prompt = "Write an instruction describing this Java method:\n" + code;

                try {
                    String response = JavaLLMClient.chatComplete(prompt, 64);
                    if (response != null) {
                        obj.put("instruction", response);
                        writer.write(obj.toString());
                        writer.newLine();
                        count++;
                    }
                } catch (Exception e) {
                    System.err.println("⚠️ Skipping due to LLM error: " + e.getMessage());
                }
            }
        }

        System.out.println("✅ step2_c_i.jsonl created: " + count + " items.");
    }
}
