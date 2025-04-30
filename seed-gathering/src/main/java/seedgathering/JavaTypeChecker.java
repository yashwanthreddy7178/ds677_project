// JavaTypeChecker.java
package seedgathering;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTypeChecker {

    public static boolean compiles(String content) {
        try {
            // Extract class name
            String className = extractClassName(content);
            if (className == null) className = "TempClass";

            // Wrap method into a class if not already present
            String fullCode = wrapInClass(content, className);

            // Create temp directory
            Path tempDir = Files.createTempDirectory("typecheck");
            File javaFile = new File(tempDir.toFile(), className + ".java");
            try (FileWriter writer = new FileWriter(javaFile)) {
                writer.write(fullCode);
            }

            // Compile
            Process process = new ProcessBuilder("javac", javaFile.getAbsolutePath())
                    .redirectErrorStream(true)
                    .start();

            int exitCode = process.waitFor();

            // Cleanup
            javaFile.delete();
            new File(tempDir.toFile(), className + ".class").delete();
            tempDir.toFile().delete();

            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static String wrapInClass(String methodCode, String className) {
        return "public class " + className + " {\n" + methodCode + "\n}";
    }

    private static String extractClassName(String content) {
        Pattern classPattern = Pattern.compile("class\\s+(\\w+)");
        Matcher matcher = classPattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    // For testing
    public static void main(String[] args) throws IOException {
        String test = "/** Convert inches */\npublic double convert(double inches) { return inches * 0.0254; }";
        System.out.println("Compiles? " + compiles(test));
    }
}
