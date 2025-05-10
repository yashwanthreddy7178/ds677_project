package step4;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class ConvertToTrainingFormat {

    public static void main(String[] args) throws IOException {
        File input = new File("seeds/step3_verified.jsonl");
        File output = new File("data/final_training_data.jsonl");
        output.getParentFile().mkdirs();

        ObjectMapper mapper = new ObjectMapper();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String prompt = obj.get("instruction").asText();
                String completion = obj.get("response").asText();

                ObjectNode trainObj = mapper.createObjectNode();
                trainObj.put("prompt", prompt);
                trainObj.put("completion", completion);

                writer.write(trainObj.toString());
                writer.newLine();
                count++;
            }
        }

        System.out.println("✅ Converted " + count + " records to training format at data/final_training_data.jsonl");
    }
} 
