// JavaParserManager.java
package seedgathering;

import ch.usi.si.seart.treesitter.*;
import ch.usi.si.seart.treesitter.exception.parser.ParsingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class JavaParserManager implements AutoCloseable {

    private final ObjectMapper mapper = new ObjectMapper();
    private final BufferedWriter writer;

    private int fileCount = 0;
    private int seedCount = 0;
    private int filterSkipped = 0;
    private int errorSkipped = 0;

    public JavaParserManager(String outputSeedPath) {
        try {
            LibraryLoader.load();

            File seedFile = new File(outputSeedPath);
            seedFile.getParentFile().mkdirs();
            writer = new BufferedWriter(new FileWriter(seedFile));

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize JavaParserManager", e);
        }
    }

    public void parseAllJavaFiles(String inputDir) {
        try (Stream<Path> fileStream = Files.walk(Paths.get(inputDir))) {
            ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

            forkJoinPool.submit(() ->
                fileStream.parallel()
                          .filter(path -> path.toString().endsWith(".java"))
                          .forEach(this::parseJavaFile)
            ).get();

            forkJoinPool.shutdown();

            System.out.println("\n📊 Parsing Summary:");
            System.out.println("📄 Total files scanned: " + fileCount);
            System.out.println("✅ Total methods written: " + seedCount);
            System.out.println("🚫 Skipped due to missing javadoc or return: " + filterSkipped);
            System.out.println("❌ Skipped due to Tree-sitter error: " + errorSkipped);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJavaFile(Path javaFilePath) {
        fileCount++;
        try {
            String content = Files.readString(javaFilePath);

            Parser parser = Parser.getFor(Language.JAVA);
            Tree tree = parser.parse(content);
            byte[] bytes = content.getBytes();
            Node rootNode = tree.getRootNode();

            List<Node> methods = findMethods(rootNode);

            for (Node method : methods) {
                try {
                    String javadoc = findJavaDoc(method, bytes);
                    if (javadoc == null || javadoc.trim().isEmpty()) {
                        filterSkipped++;
                        continue;
                    }
                    if (!hasReturnWithValue(method)) {
                        filterSkipped++;
                        continue;
                    }

                    String methodSource = extractSource(method, bytes);
                    Node methodNameNode = method.getChildByFieldName("name");
                    if (methodNameNode == null) {
                        filterSkipped++;
                        continue;
                    }

                    String methodName = extractSource(methodNameNode, bytes);
                    String fullContent = javadoc + "\n" + methodSource;

                    Map<String, String> seed = new HashMap<>();
                    seed.put("path", javaFilePath.toString());
                    seed.put("method_name", methodName);
                    seed.put("content", fullContent);

                    if (methodName != null && !methodName.isBlank() &&
                        methodSource != null && !methodSource.isBlank() &&
                        javadoc != null && !javadoc.isBlank()) {

                        String json = mapper.writeValueAsString(seed);
                        writer.write(json);
                        writer.newLine();
                        seedCount++;
                        System.out.println("Extracted seed from: " + methodName + " in " + javaFilePath.toString());
                    } else {
                        filterSkipped++;
                    }
                } catch (Exception | Error e) {
                    errorSkipped++;
                }
            }

            tree.close();
        } catch (IOException | ParsingException e) {
            errorSkipped++;
        }
    }

    public List<Node> findMethods(Node rootNode) {
        List<Node> methods = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if ("method_declaration".equals(current.getType())) {
                methods.add(current);
            }
            for (int i = 0; i < current.getChildCount(); i++) {
                queue.add(current.getChild(i));
            }
        }
        return methods;
    }

    public boolean hasReturnWithValue(Node methodNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(methodNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if ("return_statement".equals(current.getType()) && current.getChildCount() > 1) {
                return true;
            }
            for (int i = 0; i < current.getChildCount(); i++) {
                queue.add(current.getChild(i));
            }
        }
        return false;
    }

    public String extractSource(Node node, byte[] bytes) {
        int startByte = node.getStartByte();
        int endByte = node.getEndByte();

        if (startByte < 0 || endByte > bytes.length || endByte < startByte) {
            System.err.println("Invalid byte range: start=" + startByte + ", end=" + endByte);
            return "";
        }

        return new String(Arrays.copyOfRange(bytes, startByte, endByte));
    }

    public String findJavaDoc(Node methodNode, byte[] bytes) {
        Node prev = methodNode.getPrevNamedSibling();
        int safeguard = 0;
        while (prev != null && safeguard++ < 100) {
            if (prev.getType().endsWith("comment")) {
                String comment = extractSource(prev, bytes);
                if (comment.trim().startsWith("/**")) {
                    return comment;
                }
            }
            prev = prev.getPrevNamedSibling();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
