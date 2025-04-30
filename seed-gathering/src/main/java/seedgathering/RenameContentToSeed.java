package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.*;

public class RenameContentToSeed {

    public static void main(String[] args) throws IOException {
        String inputPath = "seeds/llm_verified_seeds.jsonl";
        String outputPath = "seeds/final_seeds.jsonl";

        ObjectMapper mapper = new ObjectMapper();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             SequenceWriter writer = mapper.writer().writeValues(new File(outputPath))) {

            String line;
            int converted = 0;

            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);

                if (obj.has("content")) {
                    String value = obj.remove("content").asText();
                    obj.put("seed", value);
                    writer.write(obj);
                    converted++;
                }
            }

            System.out.println("✅ Renaming completed: " + converted + " entries converted.");
        }
    }
}
