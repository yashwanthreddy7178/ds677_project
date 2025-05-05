package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.*;

public class JavaTypeChecker {
    // Use env var or default to 2 threads
    private static final int THREADS = Integer.parseInt(System.getenv().getOrDefault("TYPECHECK_THREADS", "2"));
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void runTypeCheck(String inputPath, String outputPath) throws IOException, InterruptedException {
        List<String> lines = Files.readAllLines(Paths.get(inputPath));
        int total = lines.size();
        final int BATCH_SIZE = 500; // Tune for your system

        AtomicInteger keptCount = new AtomicInteger(0);
        Path tempDir = Files.createTempDirectory("typecheck");

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath))) {
            for (int batchStart = 0; batchStart < total; batchStart += BATCH_SIZE) {
                int batchEnd = Math.min(batchStart + BATCH_SIZE, total);
                List<String> batchLines = lines.subList(batchStart, batchEnd);

                // Prepare lists for this batch
                List<String> classNames = new java.util.ArrayList<>();
                List<String> jsons = new java.util.ArrayList<>();
                List<Path> javaFiles = new java.util.ArrayList<>();
                List<String> debugJavaSources = new java.util.ArrayList<>();

                int idx = 0;
                for (String line : batchLines) {
                    try {
                        JsonNode node = mapper.readTree(line);
                        if (node == null || node.isMissingNode() || !node.isObject()) continue;
                        ObjectNode obj = (ObjectNode) node;
                        String content = obj.has("content") ? obj.get("content").asText() : obj.get("seed").asText();
                        if (content == null || content.trim().length() < 10) continue;
                        String trimmed = content.trim();
                        String fullCode;
                        // If it looks like a full class/interface/enum, use as-is
                        if (trimmed.matches("(?s).*(class|interface|enum)\\s+\\w+.*\\{.*")) {
                            fullCode = trimmed;
                        } else {
                            // Otherwise, wrap as a class
                            String className = "TempClass" + (batchStart + idx);
                            fullCode = "public class " + className + " {\n" + content + "\n}";
                        }
                        String className = extractClassName(fullCode);
                        if (className == null) className = "TempClass" + (batchStart + idx);
                        Path javaFile = tempDir.resolve(className + ".java");
                        Files.writeString(javaFile, fullCode);
                        classNames.add(className);
                        jsons.add(mapper.writeValueAsString(obj));
                        javaFiles.add(javaFile);
                        debugJavaSources.add(fullCode);
                    } catch (Exception e) {
                        // skip
                    }
                    idx++;
                }

                if (javaFiles.isEmpty()) continue;

                // Build javac command
                List<String> command = new java.util.ArrayList<>();
                command.add("javac");
                for (Path f : javaFiles) command.add(f.toString());

                ProcessBuilder pb = new ProcessBuilder(command);
                pb.redirectErrorStream(true);
                Process process = pb.start();
                StringBuilder output = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                }
                process.waitFor();

                // For each file, check if .class exists (success or only symbol errors)
                for (int i = 0; i < classNames.size(); i++) {
                    String className = classNames.get(i);
                    Path classFile = tempDir.resolve(className + ".class");
                    Path javaFile = javaFiles.get(i);
                    boolean compiled = Files.exists(classFile);
                    if (compiled) {
                        writer.write(jsons.get(i));
                        writer.newLine();
                        keptCount.incrementAndGet();
                    }
                    // Clean up
                    Files.deleteIfExists(classFile);
                    Files.deleteIfExists(javaFile);
                }

                System.out.println("✅ TypeChecked batch " + (batchEnd) + " / " + total + " (kept: " + keptCount.get() + ")");
            }
        }

        // Clean up temp directory
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(tempDir)) {
            for (Path entry : stream) {
                Files.deleteIfExists(entry);
            }
        }
        Files.deleteIfExists(tempDir);

        System.out.println("\n✅ TypeCheck Completed: " + keptCount.get() + " / " + total + " seeds kept");
    }

    private static String extractClassName(String content) {
        Matcher matcher = Pattern.compile("class\\s+(\\w+)").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }
}
