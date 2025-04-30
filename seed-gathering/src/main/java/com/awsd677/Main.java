// Main.java
package com.awsd677;

import seedgathering.DatasetManager;
import seedgathering.DownloadManager;
import seedgathering.JavaParserManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("\uD83D\uDE80 Starting Seed Gathering Project for Java...");
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Arch: " + System.getProperty("os.arch"));

        // Step 0: Get Hugging Face token from system or environment variable
        String token = System.getProperty("HF_TOKEN");
        if (token == null) {
            token = System.getenv("HF_TOKEN");
        }

        if (token == null || token.isEmpty()) {
            System.err.println("❌ Hugging Face token not provided. Use -DHF_TOKEN=... or set HF_TOKEN env variable.");
            return;
        }

        // Step 1: Fetch metadata if not already available using Python script
        String pythonScript = "scripts/fetch_metadata.py";
        String outputPath = "data/java_metadata.jsonl";

        File metadataFile = new File(outputPath);
        if (!metadataFile.exists()) {
            System.out.println("📥 Metadata file not found. Running Python script to generate...");
            try {
                ProcessBuilder pb = new ProcessBuilder("python", pythonScript,
                        "--token", token,
                        "--output", outputPath,
                        "--limit", "10000");
                pb.inheritIO(); // show Python script output in Java console
                Process process = pb.start();
                int exitCode = process.waitFor();

                if (exitCode != 0) {
                    System.err.println("❌ Python script failed with exit code " + exitCode);
                    return;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("✅ Metadata file found. Skipping fetch.");
        }

        // Step 2: Load metadata
        DatasetManager datasetManager = new DatasetManager();
        DownloadManager downloadManager = new DownloadManager();

        List<Map<String, String>> metadataList = datasetManager.loadDataset(outputPath);

        if (metadataList == null || metadataList.isEmpty()) {
            System.out.println("No metadata loaded. Exiting...");
            return;
        }

        System.out.println("Loaded " + metadataList.size() + " Java files metadata.");

        String outputDir = "downloaded";
        new java.io.File(outputDir).mkdirs();

        // Step 3: Download files if not already downloaded
        for (int i = 0; i < metadataList.size(); i++) {
            Map<String, String> entry = metadataList.get(i);
            downloadManager.downloadBlob(entry, outputDir);
        }

        System.out.println("✅ All files downloaded. Proceeding to parse Java files.");

        // Step 4: Parse and extract seeds
        String outputSeedPath = "seeds/seeds.jsonl";
        new java.io.File("seeds").mkdirs();

        try (JavaParserManager parserManager = new JavaParserManager(outputSeedPath)) {
            parserManager.parseAllJavaFiles(outputDir);
            System.out.println("✅ Seed Gathering Phase Completed! Seeds saved to: " + outputSeedPath);
        } catch (Exception e) {
            System.err.println("Failed during parsing phase: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 
