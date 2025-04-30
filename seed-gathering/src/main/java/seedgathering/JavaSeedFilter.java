// JavaSeedFilter.java
package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class JavaSeedFilter {

    private static final int MAX_LINES = 150;
    private static final List<String> BAD_WORDS = Arrays.asList("todo", "fixme", "bug");
    private static final List<String> BAD_IMPORTS = Arrays.asList(
            "import java.io", "import java.lang.reflect", "import java.net", "import java.nio", "import java.security"
    );

    public static void main(String[] args) {
        String inputJsonl = "seeds/seeds.jsonl";
        String outputJsonl = "seeds/filtered_seeds.jsonl";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputJsonl));
             SequenceWriter writer = new ObjectMapper().writer().writeValues(new File(outputJsonl))) {

            ObjectMapper mapper = new ObjectMapper();
            int total = 0;
            int kept = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                total++;
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                String content = obj.has("content") && obj.get("content") != null ? obj.get("content").asText() : null;
                if (content == null) continue;

                if (!isValid(content)) continue;

                writer.write(obj);
                kept++;
            }

            System.out.println("✅ Substep 2 Completed: " + kept + " / " + total + " seeds kept");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValid(String content) {
        if (content == null || content.isBlank()) return false;

        // Line count check
        long lines = content.lines().count();
        if (lines > MAX_LINES) return false;

        // Must contain return statement with a value
        if (!Pattern.compile("\\breturn\\b.*;").matcher(content).find()) return false;

        // Must contain /** and */ (javadoc)
        if (!content.contains("/**") || !content.contains("*/")) return false;

        // Must not contain TODO/FIXME/BUG
        String lower = content.toLowerCase();
        for (String bad : BAD_WORDS) {
            if (lower.contains(bad)) return false;
        }

        // Must not import bad packages
        for (String imp : BAD_IMPORTS) {
            if (content.contains(imp)) return false;
        }

        // Must have method with parameters (avoid `method()` )
        if (Pattern.compile("\\(\\s*\\)").matcher(content).find()) return false;

        return true;
    }
}
