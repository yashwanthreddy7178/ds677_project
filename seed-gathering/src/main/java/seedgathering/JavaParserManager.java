package seedgathering;

import ch.usi.si.seart.treesitter.*;
import ch.usi.si.seart.treesitter.exception.parser.ParsingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class JavaParserManager {

    private final ObjectMapper mapper = new ObjectMapper();
    private final SequenceWriter seedWriter;
    private final Parser parser;

    public JavaParserManager(String outputSeedPath) {
        try {
            // Load native Tree-sitter library
            LibraryLoader.load();

            // Prepare output file
            File seedFile = new File(outputSeedPath);
            seedFile.getParentFile().mkdirs();
            seedWriter = mapper.writer().writeValues(seedFile);

            // Initialize Tree-sitter Parser
            parser = Parser.getFor(Language.JAVA);

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize JavaParserManager", e);
        }
    }

    public void parseAllJavaFiles(String inputDir) {
        try {
            Files.walk(Paths.get(inputDir))
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(this::parseJavaFile);
        } catch (IOException e) {
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

            byte[] bytes = content.getBytes();
            Tree tree = parser.parse(javaFilePath);
            Node rootNode = tree.getRootNode();

            List<Node> methods = findMethods(rootNode);

            for (Node method : methods) {
                String methodSource = extractSource(method, bytes);
                Node methodNameNode = method.getChildByFieldName("name");

                if (methodNameNode == null) continue;
                String methodName = extractSource(methodNameNode, bytes);

                String javadoc = findJavaDoc(method, bytes);
                String combined = ((javadoc != null ? javadoc : "") + "\n" + methodSource).trim();
                Map<String, String> seed = new HashMap<>();
                seed.put("seed", combined);
                seedWriter.write(seed);
                System.out.println("Extracted seed from: " + methodName);
            }

            tree.close();
        } catch (IOException | ParsingException e) {
            e.printStackTrace();
        }
    }

    private List<Node> findMethods(Node rootNode) {
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

    private String extractSource(Node node, byte[] bytes) {
        int startByte = node.getStartByte();
        int endByte = node.getEndByte();
        return new String(Arrays.copyOfRange(bytes, startByte, endByte));
    }

    private String findJavaDoc(Node methodNode, byte[] bytes) {
        Node prev = methodNode.getPrevNamedSibling();
        while (prev != null) {
            // match any comment type (including block_comment for JavaDoc)
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
