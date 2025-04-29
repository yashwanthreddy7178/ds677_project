package com.awsd677;

import java.util.List;
import java.util.Map;

import seedgathering.DatasetManager;
import seedgathering.DownloadManager;
import seedgathering.JavaParserManager;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Starting Seed Gathering Project for Java...");

            DatasetManager datasetManager = new DatasetManager();
            DownloadManager downloadManager = new DownloadManager();

            // Load metadata
            List<Map<String, String>> metadataList = datasetManager.loadDataset("seed-gathering/data/java_metadata.jsonl"); // seed-gathering\data\java_metadata.jsonl

            if (metadataList == null || metadataList.isEmpty()) {
                System.out.println("No metadata loaded. Exiting...");
                return;
            }

            System.out.println("Loaded " + metadataList.size() + " Java files metadata.");

            String outputDir = "downloaded"; // Output folder
            new java.io.File(outputDir).mkdirs(); // Create if not exist

            // Download each file
            for (int i = 0; i < Math.min(5, metadataList.size()); i++) { // TEST: first 5 files
                Map<String, String> entry = metadataList.get(i);
                downloadManager.downloadBlob(entry, outputDir);
            }

            System.out.println("Downloaded some Java files. Seed Gathering Step 2 Completed!");
            
            // After downloading blobs
            System.out.println("Starting Java Parsing Phase...");

            new java.io.File("seeds").mkdirs(); // Create if not exist
            JavaParserManager parserManager = new JavaParserManager("seeds/seeds.jsonl");
            parserManager.parseAllJavaFiles(outputDir);
            // parserManager.parseAllJavaFiles("downloaded/");
            parserManager.close();

            System.out.println("Seed Gathering Step 3 Completed!");
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, "Unexpected error occurred", e);
            System.out.println("Unexpected error occurred. Program terminated.");
        }
    }
}
