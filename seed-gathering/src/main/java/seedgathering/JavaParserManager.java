// JavaParserManager.java
package seedgathering;

import ch.usi.si.seart.treesitter.*;
import ch.usi.si.seart.treesitter.exception.parser.ParsingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class JavaParserManager implements AutoCloseable {

    private final ObjectMapper mapper = new ObjectMapper();
    private final SequenceWriter seedWriter;

    public JavaParserManager(String outputSeedPath) {
        try {
            // Load native Tree-sitter library
            LibraryLoader.load();

            // Prepare output file
            File seedFile = new File(outputSeedPath);
            seedFile.getParentFile().mkdirs();
            seedWriter = mapper.writer().writeValues(seedFile);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJavaFile(Path javaFilePath) {
        try {
            String content = Files.readString(javaFilePath);

            if (content.length() > 50000) {
                System.out.println("Skipping huge file: " + javaFilePath.toString());
                return;
            }

            Parser parser = Parser.getFor(Language.JAVA); // ✅ per-file parser instance
            Tree tree = parser.parse(content);
            byte[] bytes = content.getBytes();
            Node rootNode = tree.getRootNode();

            List<Node> methods = findMethods(rootNode);

            for (Node method : methods) {
                try {
                    String javadoc = findJavaDoc(method, bytes);
                    if (javadoc == null || javadoc.trim().isEmpty()) continue;

                    if (!hasReturnWithValue(method)) continue;

                    String methodSource = extractSource(method, bytes);
                    Node methodNameNode = method.getChildByFieldName("name");
                    if (methodNameNode == null) continue;

                    String methodName = extractSource(methodNameNode, bytes);
                    String fullContent = javadoc + "\n" + methodSource;

                    Map<String, String> seed = new HashMap<>();
                    seed.put("path", javaFilePath.toString());
                    seed.put("method_name", methodName);
                    seed.put("content", fullContent);

                    // ✅ Validate content before writing
                    if (methodName != null && !methodName.isBlank() &&
                        methodSource != null && !methodSource.isBlank() &&
                        javadoc != null && !javadoc.isBlank()) {

                        seedWriter.write(seed);
                        System.out.println("Extracted seed from: " + methodName + " in " + javaFilePath.toString());
                    } else {
                        System.err.println("Skipping invalid seed for: " + javaFilePath);
                    }
                } catch (Exception | Error e) {
                    System.err.println("Skipping method due to Tree-sitter error: " + e.getMessage());
                }
            }

            tree.close();
        } catch (IOException | ParsingException e) {
            e.printStackTrace();
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

        // FIXED: Handle invalid byte ranges safely
        if (startByte < 0 || endByte > bytes.length || endByte < startByte) {
            System.err.println("Invalid byte range: start=" + startByte + ", end=" + endByte + ", length=" + bytes.length);
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
            if (seedWriter != null) {
                seedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
