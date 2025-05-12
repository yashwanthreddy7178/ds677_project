# Java Self-Alignment Pipeline with StarCoder2

This project implements a complete self-alignment pipeline for Java code generation using the **StarCoder2** language model, inspired by the SelfCodeAlign project. The goal is to fine-tune an LLM (StarCoder 3B) using filtered Java functions and aligned instruction-completion pairs.

---

## 🔧 Features

- ✅ Java parsing using Tree-sitter
- ✅ Metadata-based file download from Software Heritage
- ✅ Seed filtering (Static, Type-check, LLM validation)
- ✅ Instruction generation (S→C → C→I → I→R)
- ✅ StarCoder2 fine-tuning using HuggingFace Trainer
- ✅ Support for Azure OpenAI and Together.ai APIs
- ✅ Final model inference and qualitative evaluation

---

## 📁 Project Structure

```
.
├── seedgathering/
  ├── data/                         # Metadata and raw dataset
  ├── downloaded/                  # Java source files
  ├── seeds/                       # Intermediate seed files (filtered, typechecked, verified)
  ├── src/main
      ├── java/
        ├── com/awsd677/           # Main Class
        ├── seedgathering/         # Step1
        ├── step2/
        ├── step3/
        ├── step4/         
  │   ├── resources/                
  ├── scripts/                     # Python helper scripts (e.g., metadata fetch)
├── notebooks/                   # Training and inference notebooks (Jupyter)
├── output/                      # Model checkpoints and logs
└── README.md
```

---

## 🚀 Step-by-Step Pipeline

### Step 1: Seed Gathering (Java)
- `DatasetManager.java` → Loads metadata.jsonl
- `DownloadManager.java` → Downloads blobs from Software Heritage
- `JavaParserManager.java` → Uses Tree-sitter to extract methods with Javadoc
- `JavaSeedFilter.java` → Applies static rule-based filters
- `JavaTypeChecker.java` → Ensures seeds are syntactically valid
- `LLMSemanticFilter.java` → Uses Azure OpenAI/Together.ai to verify semantic match

### Step 2: Instruction Generation (Java)
- `SeedToComment.java` → Generates comment from code
- `CommentToInstruction.java` → Generates instruction from comment
- `InstructionToCode.java` → Generates completions from instruction

### Step 3: Fine-Tuning (Python)
- Fine-tunes `bigcode/starcoder2-3b` on prepared JSONL
- Utilizes HuggingFace Transformers with optional LoRA
- Runs on A100 (Lambda GPU) using notebooks

---

## 📦 Output Files

- `seeds/final_seeds.jsonl` → Aligned instruction/completion pairs
- `step2_s_c.jsonl`, `step2_c_i.jsonl`, `step2_i_r.jsonl` → Intermediate instruction mappings
- `output/starcoder3b/` → Trained model and logs

---

## 🧠 Model Example Output

**Prompt:**
```
Write a Java method to compute factorial recursively.
```

**Generated Completion:**
```java
public int factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}
```

---

## 💡 Credits

Inspired by the [SelfCodeAlign](https://github.com/bigcode-project/selfcodealign) pipeline.
Uses StarCoder2 models from HuggingFace (BigCode).

---

## 📜 License

MIT License
