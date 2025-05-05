package com.awsd677;

import seedgathering.DatasetManager;
import seedgathering.DownloadManager;
import seedgathering.JavaParserManager;
import seedgathering.SeedProcessor;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Seed Gathering Project for Java...");

        DatasetManager datasetManager = new DatasetManager();
        DownloadManager downloadManager = new DownloadManager();

        // Load metadata  seed-gathering/data/java_metadata.jsonl
        List<Map<String, String>> metadataList = datasetManager.loadDataset("seed-gathering/data/java_metadata.jsonl");

        if (metadataList == null || metadataList.isEmpty()) {
            System.out.println("No metadata loaded. Exiting...");
            return;
        }

        System.out.println("Loaded " + metadataList.size() + " Java files metadata.");

        String outputDir = "downloaded"; // Output folder
        new java.io.File(outputDir).mkdirs(); // Create if not exist

        // Download each file
        for (int i = 0; i < Math.min(50, metadataList.size()); i++) { // TEST: first 50 files
        //for (int i = 0; i < metadataList.size(); i++) {   // No Test
            Map<String, String> entry = metadataList.get(i);
            downloadManager.downloadBlob(entry, outputDir);
        }

        System.out.println("Downloaded some Java files. Seed Gathering Step 2 Completed!");
        
 
        // After downloading blobs
        System.out.println("Starting Java Parsing Phase...");

        JavaParserManager parserManager = new JavaParserManager("seeds/seeds.jsonl");
        parserManager.parseAllJavaFiles("downloaded/");
        parserManager.close();

        System.out.println("Seed Gathering Step 3 Completed!");

        // step 2
        System.out.println("Starting Seed Processing...");
        SeedProcessor processor = new SeedProcessor();
        processor.process("seeds/seeds.jsonl", "seeds/filtered_seeds.jsonl");
    }
}
