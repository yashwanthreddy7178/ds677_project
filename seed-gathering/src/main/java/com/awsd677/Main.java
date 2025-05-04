// Main.java
package com.awsd677;

import seedgathering.DatasetManager;
import seedgathering.DownloadManager;
import seedgathering.JavaParserManager;
import seedgathering.JavaSeedFilter;
import seedgathering.JavaTypeChecker;
import seedgathering.LLMSemanticFilter;
import seedgathering.RenameContentToSeed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\uD83D\uDE80 Starting Seed Gathering Project for Java...");
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("Arch: " + System.getProperty("os.arch"));

        String token = System.getProperty("HF_TOKEN");
        if (token == null) {
            token = System.getenv("HF_TOKEN");
        }

        if (token == null || token.isEmpty()) {
            System.err.println("❌ Hugging Face token not provided. Use -DHF_TOKEN=... or set HF_TOKEN env variable.");
            return;
        }

        String pythonScript = "scripts/fetch_metadata.py";
        String outputPath = "data/java_metadata.jsonl";

        File metadataFile = new File(outputPath);
        if (!metadataFile.exists()) {
            System.out.println("📥 Metadata file not found. Running Python script to generate...");
            try {
                ProcessBuilder pb = new ProcessBuilder("python", pythonScript,
                        "--token", token,
                        "--output", outputPath,
                        "--limit", "50000");
                pb.inheritIO();
                Process process = pb.start();
                int exitCode = process.waitFor();

                if (exitCode != 0 && !metadataFile.exists()) {
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

        DatasetManager datasetManager = new DatasetManager();
        DownloadManager downloadManager = new DownloadManager();

        List<Map<String, String>> metadataList = datasetManager.loadDataset("data/java_metadata.jsonl");
        if (metadataList == null || metadataList.isEmpty()) {
            System.out.println("No metadata loaded. Exiting...");
            return;
        }

        System.out.println("Loaded " + metadataList.size() + " Java files metadata.");

        String outputDir = "downloaded";
        new File(outputDir).mkdirs();

        for (int i = 0; i < metadataList.size(); i++) {
            Map<String, String> entry = metadataList.get(i);
            downloadManager.downloadBlob(entry, outputDir);
        }

        System.out.println("✅ Files downloaded. Proceeding to Java parsing...");

        File seedsFile = new File("seeds/seeds.jsonl");
        if (!seedsFile.exists()) {
            try (JavaParserManager parserManager = new JavaParserManager("seeds/seeds.jsonl")) {
                parserManager.parseAllJavaFiles(outputDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("✅ seeds.jsonl found. Skipping Java parsing.");
        }

        System.out.println("🔎 Running Static Filter (Substep 2)...");
        File filteredSeedsFile = new File("seeds/filtered_seeds.jsonl");
        if (!filteredSeedsFile.exists()) {
            JavaSeedFilter.main(null);
        } else {
            System.out.println("✅ filtered_seeds.jsonl found. Skipping static filter.");
        }

        System.out.println("🧪 Running Type Checker...");
        File typecheckSeedsFile = new File("seeds/typecheck_seeds.jsonl");
        if (!typecheckSeedsFile.exists()) {
            try {
                JavaTypeChecker.runTypeCheck("seeds/filtered_seeds.jsonl", "seeds/typecheck_seeds.jsonl");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("✅ typecheck_seeds.jsonl found. Skipping type check.");
        }

        System.out.println("🤖 Running LLM Semantic Filter (Substep 3)...");
        File llmVerifiedFile = new File("seeds/llm_verified_seeds.jsonl");
        if (!llmVerifiedFile.exists()) {
            try {
                LLMSemanticFilter.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("✅ llm_verified_seeds.jsonl found. Skipping LLM filter.");
        }

        System.out.println("✅ LLM filter complete. Proceeding to RenameContentToSeed step...");
        try {
            RenameContentToSeed.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("🎉 All steps completed. Final verified seeds in seeds/final_seeds.jsonl");
    }
}
