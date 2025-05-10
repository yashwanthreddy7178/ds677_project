// src/step3/MainStep3.java
package step3;

import java.io.File;

public class MainStep3 {
    public static void main(String[] args) {
        System.out.println("\uD83D\uDD04 Starting Step 3: Self-Validation...");

        File input = new File("seeds/step2_i_r.jsonl");
        File output = new File("seeds/step3_verified.jsonl");

        if (!input.exists()) {
            System.err.println("❌ Input file step2_i_r.jsonl not found. Run Step 2 first.");
            return;
        }

        if (output.exists()) {
            System.out.println("✅ step3_verified.jsonl already exists. Skipping Step 3.");
            return;
        }

        try {
            InstructionResponseFilter.main(null);
        } catch (Exception e) {
            System.err.println("❌ Self-validation step failed.");
            e.printStackTrace();
            return;
        }

        System.out.println("\u2705 Step 3 completed. Output: step3_verified.jsonl");
    }
} 
