package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    public Node add(Node node) {
        children.add(node);
        return node;
    }
}
