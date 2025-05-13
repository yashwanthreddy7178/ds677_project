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

public class JavaTypeChecker {

    private static final int THREADS = 4; // Adjusted thread pool size for better performance
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void runTypeCheck(String inputPath, String outputPath) throws IOException, InterruptedException {
        List<String> lines = Files.readAllLines(Paths.get(inputPath));
        int total = lines.size();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Future<List<String>>> results = new ArrayList<>();

        AtomicInteger keptCount = new AtomicInteger(0);

        int batchSize = 500; // Increased batch size for better throughput
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

                        if (compiles(content)) { // Use in-memory compilation
                            keptCount.incrementAndGet();
                            processedBatch.add(mapper.writeValueAsString(obj));
                        }
                    } catch (Exception e) {
                        // Skip invalid lines
                    }
                }

                // Log progress after processing the batch
                System.out.println("✅ TypeCheck Completed: " + keptCount.get() + " / " + total + " seeds kept");
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

        System.out.println("\n✅ TypeCheck Completed: " + keptCount.get() + " / " + total + " seeds kept");
    }

    private static boolean compiles(String content) {
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
            // Only allow code that compiles (success == true)
            return success;
        } catch (Exception e) {
            // If an exception occurs, treat as not compilable
            return false;
        }
    }

    private static String extractClassName(String content) {
        Matcher matcher = Pattern.compile("class\\s+(\\w+)").matcher(content);
        return matcher.find() ? matcher.group(1) : null;
    }
}
