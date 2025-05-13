import json
import argparse
import os
from datasets import load_dataset

def fetch_metadata(token: str, output_path: str, limit: int):
    print("Streaming dataset with Hugging Face token...")
    ds = load_dataset(
        "bigcode/the-stack-v2",
        "Java",
        split="train",
        streaming=True,
        token=token
    )

    os.makedirs(os.path.dirname(output_path), exist_ok=True)

    with open(output_path, "w", encoding="utf-8") as f:
        for i, sample in enumerate(ds.take(limit)):
            try:
                metadata = {
                    "blob_id": sample["blob_id"],
                    "src_encoding": sample["src_encoding"],
                    "path": sample["path"]
                }
                f.write(json.dumps(metadata) + "\n")
            except Exception as e:
                print("Error at sample")

    print(f"✅ Finished writing {limit} entries to: {output_path}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Stream and fetch metadata from the-stack-v2-dedup (Java)")
    parser.add_argument("--token", type=str, help="Hugging Face token", default=os.getenv("HF_TOKEN"))
    parser.add_argument("--output", type=str, default="data/java_metadata.jsonl", help="Output .jsonl file path")
    parser.add_argument("--limit", type=int, default=10000, help="Number of samples to download")

    args = parser.parse_args()

    if not args.token:
        print("❌ Hugging Face token not provided. Use --token or set HF_TOKEN env variable.")
        exit(1)

    fetch_metadata(args.token, args.output, args.limit)
