package seedgathering;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatasetManager {

    private final ObjectMapper mapper = new ObjectMapper();

    // Load dataset from JSONL (each line is one JSON object)
    public List<Map<String, String>> loadDataset(String path) {
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Map<String, String> entry = mapper.readValue(line, Map.class);
                data.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }

    // Save dataset to a JSON file (optional)
    public void saveDataset(List<Map<String, String>> data, String path) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), data);
            System.out.println("Dataset saved to: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
