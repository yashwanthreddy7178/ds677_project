package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class RenameContentToSeed {

    public static void main(String[] args) throws IOException {
        String inputPath = "seeds/llm_verified_seeds.jsonl";
        String outputPath = "seeds/final_seeds.jsonl";

        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;
            int converted = 0;

            while ((line = reader.readLine()) != null) {
                JsonNode node = mapper.readTree(line);
                if (node != null && node.isObject()) {
                    ObjectNode obj = (ObjectNode) node;

                    if (obj.has("content")) {
                        String value = obj.remove("content").asText();
                        obj.put("seed", value);
                        writer.write(mapper.writeValueAsString(obj));
                        writer.newLine();
                        converted++;
                    } else {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            System.out.println("✅ Renaming completed: " + converted + " entries converted.");
        }
    }
} 
