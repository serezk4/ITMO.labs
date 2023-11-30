package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.serezka.parser.formats.tree.Node;

import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor @Getter
public abstract class Format {
    public abstract void format(List<String> list);
    public abstract void format(Map<String, String> map);

    public abstract String get();
}
