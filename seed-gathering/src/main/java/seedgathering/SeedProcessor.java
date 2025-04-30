package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.*;
import java.util.*;

public class SeedProcessor {

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<Map<String, String>> filteredSeeds = new ArrayList<>();

    public void process(String inputPath, String outputPath) {
        int total = 0, kept = 0, withJavadoc = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                total++;
                Map<String, String> seed = mapper.readValue(line, Map.class);
                String content = seed.get("seed");
                if (content == null || content.strip().length() < 20) continue;

                // Basic logic: skip trivial methods (e.g., one-liners)
                long codeLines = content.lines()
                        .filter(l -> !l.strip().isEmpty())
                        .filter(l -> !l.strip().startsWith("//"))
                        .count();
                if (codeLines < 3) continue;

                if (content.contains("/**")) withJavadoc++;

                filteredSeeds.add(Map.of("seed", content));
                kept++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Save filtered seeds
        try {
            File outFile = new File(outputPath);
            outFile.getParentFile().mkdirs();
            SequenceWriter writer = mapper.writer().writeValues(outFile);
            for (Map<String, String> seed : filteredSeeds) {
                writer.write(seed);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print stats
        System.out.println("Step 2 Summary:");
        System.out.println("Total seeds read: " + total);
        System.out.println("Seeds kept (≥3 lines): " + kept);
        System.out.println("Seeds with Javadoc: " + withJavadoc);
        System.out.println("Filtered seeds saved to: " + outputPath);
    }
}
