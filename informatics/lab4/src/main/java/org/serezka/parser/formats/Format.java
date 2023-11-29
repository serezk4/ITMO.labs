package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor @Getter
public abstract class Format {
    public abstract Node add(List<String> data);
    public abstract Node add(String data);

    public abstract Node insert(Node node, List<String> data);
    public abstract Node insert(Node node, String data);

    public abstract String get();
}
