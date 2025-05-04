// DownloadManager.java
package seedgathering;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;

public class DownloadManager {

    private static final String S3_BASE_URL = "https://softwareheritage.s3.amazonaws.com/content/";

    public void downloadBlob(Map<String, String> metadata, String outputDir) {
        String blobId = metadata.get("blob_id");
        String encoding = metadata.getOrDefault("src_encoding", "UTF-8");
        String path = metadata.get("path");

        if (blobId == null || path == null) {
            System.out.println("Invalid metadata: missing blob_id or path.");
            return;
        }

        try {
            File outputFile = new File(outputDir, path);

            if (outputFile.exists()) {
                System.out.println("Already downloaded: " + outputFile.getPath());
                return;
            }

            String blobUrl = S3_BASE_URL + blobId;
            URL url = new URL(blobUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                GZIPInputStream gzipInputStream = new GZIPInputStream(connection.getInputStream());
                InputStreamReader reader = new InputStreamReader(gzipInputStream, encoding);
                BufferedReader bufferedReader = new BufferedReader(reader);

                outputFile.getParentFile().mkdirs();

                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                }

                gzipInputStream.close();
                bufferedReader.close();
                System.out.println("Downloaded and saved: " + outputFile.getPath());
            } else {
                System.out.println("Failed to download blob: " + blobId + " | HTTP " + connection.getResponseCode());
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 