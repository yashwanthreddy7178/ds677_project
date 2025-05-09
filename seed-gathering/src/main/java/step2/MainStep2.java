package step2;

import java.io.File;

public class MainStep2 {
    public static void main(String[] args) {
        System.out.println("\uD83D\uDD04 Starting Step 2: Instruction Generation...");

        // Step 2.1 - Split seed to instruction + code
        File sToCFile = new File("seeds/step2_s_c.jsonl");
        if (!sToCFile.exists()) {
            try {
                Step2SplitSeed.main(null);
            } catch (Exception e) {
                System.err.println("❌ Failed during Step 2.1 (S->C)");
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("✅ step2_s_c.jsonl already exists. Skipping S->C.");
        }

        // Step 2.2 - Generate instruction from code
        System.out.println("\uD83D\uDD04 Starting Step 2.2: Code to Instruction Generation...");
        File cToIFile = new File("seeds/step2_c_i.jsonl");
        if (!cToIFile.exists()) {
            try {
                CodeToInstruction.main(null);
            } catch (Exception e) {
                System.err.println("❌ Failed during Step 2.2 (C->I)");
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("✅ step2_c_i.jsonl already exists. Skipping C->I.");
        }

        // Step 2.3 - Generate response from instruction
        System.out.println("\uD83D\uDD04 Starting Step 2.3: Instruction to Code Generation...");
        File iToRFile = new File("seeds/step2_i_r.jsonl");
        if (!iToRFile.exists()) {
            try {
                InstructionToCode.main(null);
            } catch (Exception e) {
                System.err.println("❌ Failed during Step 2.3 (I->R)");
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("✅ step2_i_r.jsonl already exists. Skipping I->R.");
        }

        System.out.println("\u2705 Step 2 complete! Final output: step2_i_r.jsonl");
    }
} 
