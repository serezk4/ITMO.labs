package org.serezka.parser.formats.tree;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class Node {
    Value value;

    public Node(Value value) {
        this.value = value;
    }

    @NonFinal
    List<Node> children = new ArrayList<>();

    public void add(Node node) {
        children.add(node);
    }

    public Node append(Node node) {
        children.add(node);
        return this;
    }

    public Node insert(Node node) {
        children.add(node);
        return node;
    }
}
