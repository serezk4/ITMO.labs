package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor @Getter
public abstract class Format {
    public abstract void add(List<String> data);
    public abstract void add(String data);

    public abstract void insert(Node node, List<String> data);
    public abstract void insert(Node node, String data);

    public abstract String get();
}
