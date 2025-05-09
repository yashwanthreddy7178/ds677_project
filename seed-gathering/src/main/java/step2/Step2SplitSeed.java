package step2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;

public class Step2SplitSeed {

    public static void main(String[] args) throws IOException {
        File input = new File("seeds/final_seeds.jsonl");
        File output = new File("seeds/step2_s_c.jsonl");

        ObjectMapper mapper = new ObjectMapper();
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String seed = obj.get("seed").asText();

                int start = seed.indexOf("/**");
                int end = seed.indexOf("*/");

                if (start == -1 || end == -1) continue;

                String doc = seed.substring(start + 3, end).replace("*", "").trim();
                String code = seed.substring(end + 2).trim();

                if (doc.isBlank() || code.isBlank()) continue;

                ObjectNode out = mapper.createObjectNode();
                out.put("instruction", doc);
                out.put("canonical_solution", code);

                writer.write(out.toString());
                writer.newLine();
                count++;
            }
        }

        System.out.println("✅ step2_s_c.jsonl created: " + count + " items.");
    }
}
