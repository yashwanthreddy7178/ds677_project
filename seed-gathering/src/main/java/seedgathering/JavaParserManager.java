package seedgathering;

import ch.usi.si.seart.treesitter.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public class JavaParserManager {

    private final ObjectMapper mapper = new ObjectMapper();
    private final SequenceWriter seedWriter;
    private final Parser parser;
    private int seedId = 0; // add seedId to the seed map
    private long maxparser = 1000; // max parser size

    public JavaParserManager(String outputSeedPath) {
        try {
            // Load native Tree-sitter library
            LibraryLoader.load();

            // Prepare output file
            File seedFile = new File(outputSeedPath);
            seedFile.getParentFile().mkdirs();
            seedWriter = mapper.writer().writeValues(seedFile);

            // Initialize Tree-sitter Parser
            //parser = new Parser(maxparser,Language.JAVA);
            parser = new Parser(); 
            parser.setLanguage(Language.JAVA);
            //parser = new Parser(); 
            //parser.setLanguage(Language.load("tree-sitter-java"));

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
    /*
    private void parseJavaFile(Path javaFilePath) {
        try {
            String content = Files.readString(javaFilePath);

            if (content.length() > 50000) {
                System.out.println("Skipping huge file: " + javaFilePath.toString());
                return;
            }

            byte[] bytes = content.getBytes();
            Tree tree = parser.parse(bytes);
            Node rootNode = tree.getRootNode();

            List<Node> methods = findMethods(rootNode);

            for (Node method : methods) {
                String methodSource = extractSource(method, bytes);
                Node methodNameNode = method.getChildByFieldName("name");

                if (methodNameNode == null) continue;
                String methodName = methodNameNode.getText();

                String javadoc = findJavaDoc(method, bytes);

                if (javadoc != null) {
                    Map<String, String> seed = new HashMap<>();
                    seed.put("path", javaFilePath.toString());
                    seed.put("method_name", methodName);
                    seed.put("code", methodSource);
                    seed.put("javadoc", javadoc);

                    seedWriter.write(seed);
                    System.out.println("Extracted seed from: " + methodName + " in " + javaFilePath.toString());
                }
            }

            tree.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    private void parseJavaFile(Path javaFilePath) {
        try {
            String content = Files.readString(javaFilePath);

            if (content.length() > 50000) {
                System.out.println("Skipping huge file: " + javaFilePath.toString());
                return;
            }

            byte[] bytes = content.getBytes();
            //Tree tree = parser.parse(bytes);
            Tree tree = parser.parse("class A {}"); 
            Node rootNode = tree.getRootNode();

            List<Node> seeds = new ArrayList<>();
            
            // class - method - comment
            seeds.addAll(findNodes(rootNode, "class_declaration"));
            seeds.addAll(findNodes(rootNode, "method_declaration"));
            seeds.addAll(findNodes(rootNode, "comment"));

            for (Node node : seeds) {
                String extracted = extractSource(node, bytes).trim();
                if (!extracted.isEmpty()) {
                    Map<String, String> seed = new HashMap<>();
                    seedId++;
                    //seed.put("id", seedId);
                    seed.put("path", javaFilePath.toString());
                    seed.put("seed", extracted);

                    seedWriter.write(seed);
                    System.out.println("Extracted seed from: " + javaFilePath.toString());
                }
            }

            tree.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
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
    */
    private List<Node> findNodes(Node rootNode, String type) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (type.equals(current.getType())) {
                nodes.add(current);
            }
            for (int i = 0; i < current.getChildCount(); i++) {
                queue.add(current.getChild(i));
            }
        }

        return nodes;
    }
    private String extractSource(Node node, byte[] bytes) {
        int startByte = node.getStartByte();
        int endByte = node.getEndByte();
        // prevent
        if (startByte < 0 || endByte > bytes.length || startByte >= endByte) {
            return "";
        }
        return new String(Arrays.copyOfRange(bytes, startByte, endByte));
    }

    private String findJavaDoc(Node methodNode, byte[] bytes) {
        Node prev = methodNode.getPrevNamedSibling();
        while (prev != null) {
            if ("comment".equals(prev.getType())) {
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
