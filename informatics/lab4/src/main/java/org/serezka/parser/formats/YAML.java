//package org.serezka.parser.formats;
//
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.serezka.parser.formats.tree.Node;
//
//import java.io.Serializable;
//import java.util.List;
//
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class YAML extends Format implements Serializable {
//    Node root = new Node(new Value().put(""));
//
//    @Override
//    public Node add(List<String> data) {
//        root.getChildren().stream().map(Node::getValue).forEach(System.out::println);
//
//        return root.add(new Node(new Value().put(data)));
//    }
//
//    @Override
//    public Node add(String data) {
//        return root.add(new Node(new Value().put(data)));
//    }
//
//    @Override
//    public Node insert(Node node, List<String> data) {
//        return node.add(new Node(new Value().put(data)));
//    }
//
//    @Override
//    public Node insert(Node node, String data) {
//        return node.add(new Node(new Value().put(data)));
//    }
//
//    @Override
//    public String get() {
//        return null;
//    }
//}
