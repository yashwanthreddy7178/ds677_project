package seedgathering;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TypeChecker {

    private final File tempFolder;

    public TypeChecker() {
        tempFolder = new File("temp_mypy");
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    /**
     * Typechecks the given function code using mypy.
     *
     * @param functionCode Python function code as a String
     * @return true if typechecking passed, false otherwise
     */
    public boolean typeCheckFunction(String functionCode) {
        try {
            // Create temporary file
            File tempFile = File.createTempFile("func_", ".py", tempFolder);
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(functionCode);
            }

            // Run mypy
            ProcessBuilder pb = new ProcessBuilder("mypy", "--ignore-missing-imports", tempFile.getAbsolutePath());
            pb.redirectErrorStream(true); // combine stdout + stderr
            Process process = pb.start();

            int exitCode = process.waitFor();

            tempFile.delete();  // clean up after checking

            return exitCode == 0;  // mypy returns 0 if no errors
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cleanUp() {
        if (tempFolder.exists()) {
            for (File file : tempFolder.listFiles()) {
                file.delete();
            }
            tempFolder.delete();
        }
    }
}
