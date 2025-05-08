// src/step2/MainStep2.java
package step2;

public class MainStep2 {
    public static void main(String[] args) {
        System.out.println("\uD83D\uDD04 Starting Step 2: Instruction Generation...");

        // Step 2.1 - Java: Split seed to instruction + code
        try {
            Step2SplitSeed.main(null);
        } catch (Exception e) {
            System.err.println("❌ Failed during Step 2.1 (S->C)");
            e.printStackTrace();
            return;
        }

        // Step 2.2 - Java: Generate instruction from code using Azure OpenAI
        try {
            CodeToInstruction.main(null);
        } catch (Exception e) {
            System.err.println("❌ Failed during Step 2.2 (C->I)");
            e.printStackTrace();
            return;
        }

        // Step 2.3 - Java: Generate code from instruction using Azure OpenAI
        try {
            InstructionToCode.main(null);
        } catch (Exception e) {
            System.err.println("❌ Failed during Step 2.3 (I->R)");
            e.printStackTrace();
            return;
        }

        System.out.println("\u2705 Step 2 complete! Final output: step2_i_r.jsonl");
    }
} 
