package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.*;

public class JavaTypeChecker {

    private static final int THREADS = 2; // ✅ LIMIT threads to reduce CPU usage
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void runTypeCheck(String inputPath, String outputPath) throws IOException, InterruptedException {
        List<String> lines = Files.readAllLines(Paths.get(inputPath));
        int total = lines.size();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Future<String>> results = new CopyOnWriteArrayList<>();

        AtomicInteger index = new AtomicInteger(0);
        AtomicInteger keptCount = new AtomicInteger(0);

        for (String line : lines) {
            int currentIndex = index.incrementAndGet();

            results.add(executor.submit(() -> {
                try {
                    JsonNode node = mapper.readTree(line);
                    if (node == null || node.isMissingNode() || !node.isObject()) return null;

                    ObjectNode obj = (ObjectNode) node;
                    String content = obj.get("content").asText();

                    if (compiles(content)) {
                        keptCount.incrementAndGet();
                        if (currentIndex % 100 == 0 || currentIndex == total) {
                            System.out.println("✅ TypeChecked seed " + currentIndex + " / " + total + " (kept: " + keptCount.get() + ")");
                        }
                        return mapper.writeValueAsString(obj);
                    }
                } catch (Exception e) {
                    // skip
                }
                return null;
            }));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.MINUTES);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (Future<String> result : results) {
                String json = result.get();
                if (json != null) {
                    writer.write(json);
                    writer.newLine();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("\n✅ TypeCheck Completed: " + keptCount.get() + " / " + total + " seeds kept");
    }

    private static boolean compiles(String content) {
        try {
            String className = extractClassName(content);
            if (className == null) className = "TempClass";

            String fullCode = "public class " + className + " {\n" + content + "\n}";

            Path tempDir = Files.createTempDirectory("typecheck");
            File javaFile = new File(tempDir.toFile(), className + ".java");
            Files.writeString(javaFile.toPath(), fullCode);

            Process process = new ProcessBuilder("javac", javaFile.getAbsolutePath())
                    .redirectErrorStream(true)
                    .start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            boolean onlySymbolErrors = true;
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.contains("cannot find symbol")) {
                    onlySymbolErrors = false;
                }
            }

            int exitCode = process.waitFor();

            // Clean up
            new File(tempDir.toFile(), className + ".class").delete();
            javaFile.delete();
            tempDir.toFile().delete();

            return exitCode == 0 || onlySymbolErrors;

        } catch (Exception e) {
            return false;
        }
    }

    private static String extractClassName(String content) {
        Matcher matcher = Pattern.compile("class\\s+(\\w+)").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }
}
