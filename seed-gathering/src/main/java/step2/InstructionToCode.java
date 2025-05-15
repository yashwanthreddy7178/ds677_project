package step2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class InstructionToCode {

    private static final int BATCH_SIZE = 50; // Increased to maximize throughput
    private static final int MAX_CONCURRENT_REQUESTS = 20; // Increased parallel processing
    private static final ExecutorService executor = Executors.newFixedThreadPool(MAX_CONCURRENT_REQUESTS);
    
    // Rate limiting variables
    private static final long RATE_LIMIT_WINDOW = 60000; // 1 minute in milliseconds
    private static final int MAX_REQUESTS_PER_MINUTE = 1800; // Slightly below 2000 RPM limit
    private static final AtomicInteger requestsInWindow = new AtomicInteger(0);
    private static long windowStartTime = System.currentTimeMillis();
    
    // Monitoring metrics
    private static volatile int totalRequests = 0;
    private static volatile int successfulRequests = 0;
    private static volatile long startTime = System.currentTimeMillis();
    private static final AtomicInteger activeRequests = new AtomicInteger(0);

    public static void main(String[] args) throws IOException, InterruptedException {
        File input = new File("seeds/step2_c_i.jsonl");
        File output = new File("seeds/step2_i_r.jsonl");

        ObjectMapper mapper = new ObjectMapper();
        startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            String line;
            List<ObjectNode> batch = new ArrayList<>();
            List<Future<?>> futures = new ArrayList<>();

            // Start monitoring thread
            startMonitoringThread();

            while ((line = reader.readLine()) != null) {
                ObjectNode obj = (ObjectNode) mapper.readTree(line);
                batch.add(obj);

                if (batch.size() >= BATCH_SIZE) {
                    futures.add(processBatchAsync(batch, writer, mapper));
                    batch = new ArrayList<>();
                    
                    // Clean up completed futures
                    futures.removeIf(Future::isDone);
                    
                    // Wait if we're hitting rate limits
                    while (activeRequests.get() >= MAX_CONCURRENT_REQUESTS) {
                        Thread.sleep(100);
                    }
                }
            }

            if (!batch.isEmpty()) {
                futures.add(processBatchAsync(batch, writer, mapper));
            }

            // Wait for all remaining futures to complete
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (Exception e) {
                    System.err.println("Error waiting for future: " + e.getMessage());
                }
            }
        } finally {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }
    }

    private static void startMonitoringThread() {
        Thread monitorThread = new Thread(() -> {
            while (true) {
                try {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    double requestsPerSecond = (double) successfulRequests / (elapsedTime / 1000.0);
                    double estimatedTimeRemaining = (250000 - successfulRequests) / requestsPerSecond;
                    
                    System.out.printf("\rProcessed: %d/%d (%.1f%%) | Rate: %.1f req/s | Est. time remaining: %.1f minutes", 
                        successfulRequests, 250000, 
                        (successfulRequests * 100.0 / 250000),
                        requestsPerSecond,
                        estimatedTimeRemaining / 60);
                    
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    private static void checkRateLimit() throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        if (currentTime - windowStartTime >= RATE_LIMIT_WINDOW) {
            requestsInWindow.set(0);
            windowStartTime = currentTime;
        }
        
        while (requestsInWindow.get() >= MAX_REQUESTS_PER_MINUTE) {
            Thread.sleep(100);
        }
        requestsInWindow.incrementAndGet();
    }

    private static Future<?> processBatchAsync(List<ObjectNode> batch, BufferedWriter writer, ObjectMapper mapper) {
        return executor.submit(() -> {
            for (ObjectNode obj : batch) {
                try {
                    checkRateLimit();
                    activeRequests.incrementAndGet();
                    
                    String instruction = obj.get("instruction").asText();
                    String prompt = "Generate a Java method implementation following these guidelines:\n" +
                        "1. Write a complete, compilable Java method\n" +
                        "2. Include proper method signature with access modifier, return type, and parameters\n" +
                        "3. Add appropriate JavaDoc comments explaining the method's purpose, parameters, and return value\n" +
                        "4. Handle edge cases and null checks where appropriate\n" +
                        "5. Follow Java coding conventions and best practices\n" +
                        "6. Include necessary imports if required\n\n" +
                        "Instruction to implement:\n" + instruction + "\n\n" +
                        "Provide only the method implementation without any additional explanation.";

                    String response = JavaLLMClient.chatComplete(prompt, 256);
                    if (response != null) {
                        synchronized (writer) {
                            ObjectNode out = mapper.createObjectNode();
                            out.put("instruction", instruction);
                            out.put("response", response);
                            writer.write(out.toString());
                            writer.newLine();
                            writer.flush();
                        }
                        successfulRequests++;
                    }
                } catch (Exception e) {
                    System.err.println("\n⚠️ Error: " + e.getMessage());
                } finally {
                    activeRequests.decrementAndGet();
                    totalRequests++;
                }
            }
        });
    }
}