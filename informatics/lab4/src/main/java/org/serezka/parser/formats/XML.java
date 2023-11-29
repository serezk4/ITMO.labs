package org.serezka.parser.formats;

import java.io.Serializable;
import java.util.List;

public class XML extends Format implements Serializable {
    Node root = new Node(new Value().put("root"));

    @Override
    public Node add(List<String> data) {

        return null;
    }

    @Override
    public Node add(String data) {

        return null;
    }

    @Override
    public Node insert(Node node, List<String> data) {

        return node;
    }

    @Override
    public Node insert(Node node, String data) {

        return node;
    }

    @Override
    public String get() {
        return null;
    }
}
