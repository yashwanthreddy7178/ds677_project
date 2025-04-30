package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class JavaSeedFilter {

    private static final int MAX_LINES = 1500;
    private static final List<String> BAD_WORDS = Arrays.asList("todo", "fixme", "bug");
    private static final List<String> BAD_IMPORTS = Arrays.asList(
            "import java.io", "import java.lang.reflect", "import java.net", "import java.nio", "import java.security"
    );

    private static final Map<String, Integer> rejectionStats = new HashMap<>();

    public static void main(String[] args) {
        String inputJsonl = "seeds/seeds.jsonl";
        String outputJsonl = "seeds/filtered_seeds.jsonl";

        ObjectMapper mapper = new ObjectMapper();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputJsonl));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputJsonl))) {

            int total = 0, kept = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                total++;

                JsonNode node = mapper.readTree(line);
                if (node == null || node.isMissingNode() || !node.isObject()) {
                    increment("Invalid or empty line");
                    continue;
                }

                ObjectNode obj = (ObjectNode) node;
                String content = obj.has("content") ? obj.get("content").asText() : null;

                if (content == null || content.isBlank()) {
                    increment("Blank content");
                    continue;
                }

                if (!isValid(content)) continue;

                writer.write(mapper.writeValueAsString(obj));
                writer.newLine(); // ✅ ensure JSONL
                kept++;
            }

            writer.flush(); // ✅ important!
            System.out.println("✅ Substep 2 Debug Completed: " + kept + " / " + total + " seeds kept");
            System.out.println("📊 Rejection breakdown:");
            rejectionStats.forEach((reason, count) ->
                System.out.printf("  - %-30s : %d%n", reason, count)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValid(String content) {
        long lines = content.lines().count();
        if (lines > MAX_LINES) {
            increment("Too many lines");
            return false;
        }

        if (!content.contains("/**") || !content.contains("*/")) {
            increment("Missing Javadoc");
            return false;
        }

        if (!Pattern.compile("\\breturn\\b.*;").matcher(content).find()) {
            increment("Missing return");
            return false;
        }

        String lower = content.toLowerCase();
        for (String bad : BAD_WORDS) {
            if (lower.contains(bad)) {
                increment("Contains bad word: " + bad);
                return false;
            }
        }

        for (String imp : BAD_IMPORTS) {
            if (content.contains(imp)) {
                increment("Contains bad import: " + imp);
                return false;
            }
        }

        return true;
    }

    private static void increment(String reason) {
        rejectionStats.merge(reason, 1, Integer::sum);
    }
}
