package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.*;

public class JavaTypeCheckerLoose {

    private static final int THREADS = 4;
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void runTypeCheck(String inputPath, String outputPath) throws IOException, InterruptedException {
        List<String> lines = Files.readAllLines(Paths.get(inputPath));
        int total = lines.size();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Future<List<String>>> results = new ArrayList<>();

        AtomicInteger keptCount = new AtomicInteger(0);

        int batchSize = 500;
        for (int i = 0; i < lines.size(); i += batchSize) {
            int start = i;
            int end = Math.min(i + batchSize, lines.size());
            List<String> batch = lines.subList(start, end);

            results.add(executor.submit(() -> {
                List<String> processedBatch = new ArrayList<>();
                for (String line : batch) {
                    try {
                        JsonNode node = mapper.readTree(line);
                        if (node == null || node.isMissingNode() || !node.isObject()) continue;

                        ObjectNode obj = (ObjectNode) node;
                        String content = obj.has("content") ? obj.get("content").asText() : obj.get("seed").asText();

                        if (looseCompiles(content)) {
                            keptCount.incrementAndGet();
                            processedBatch.add(mapper.writeValueAsString(obj));
                        }
                    } catch (Exception e) {
                        // Skip invalid lines
                    }
                }
                System.out.println("✅ LooseTypeCheck Completed: " + keptCount.get() + " / " + total + " seeds kept");
                return processedBatch;
            }));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.MINUTES);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (Future<List<String>> result : results) {
                List<String> batchResult = result.get();
                for (String json : batchResult) {
                    writer.write(json);
                    writer.newLine();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("\n✅ LooseTypeCheck Completed: " + keptCount.get() + " / " + total + " seeds kept");
    }

    private static boolean looseCompiles(String content) {
        // Allow if it compiles, or if it passes a basic syntax check (e.g., balanced braces)
        try {
            String className = extractClassName(content);
            if (className == null) className = "TempClass";

            String fullCode = "public class " + className + " {\n" + content + "\n}";

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StringWriter output = new StringWriter();
            JavaCompiler.CompilationTask task = compiler.getTask(output, null, null, null, null,
                    Collections.singletonList(new SimpleJavaFileObject(URI.create("string:///" + className + ".java"), JavaFileObject.Kind.SOURCE) {
                        @Override
                        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                            return fullCode;
                        }
                    }));

            boolean success = task.call();
            if (success) return true;
        } catch (Exception e) {
            // Ignore and fall through to syntax check
        }
        // Fallback: basic syntax check
        return isValidSyntax(content);
    }

    private static boolean isValidSyntax(String content) {
        // Very basic: checks for balanced braces and semicolons
        int open = 0, close = 0;
        for (char c : content.toCharArray()) {
            if (c == '{') open++;
            if (c == '}') close++;
        }
        boolean balanced = open == close;
        boolean hasSemicolon = content.contains(";");
        return balanced && hasSemicolon;
    }

    private static String extractClassName(String content) {
        Matcher matcher = Pattern.compile("class\\s+(\\w+)").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }
}
