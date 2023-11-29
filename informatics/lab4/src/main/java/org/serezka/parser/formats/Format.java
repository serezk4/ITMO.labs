package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor @Getter
public abstract class Format {
    StringBuilder data;

    public abstract int add(List<String> data);
    public abstract int add(String data);

    public abstract int insert(int id, List<String> data);
    public abstract int insert(int id, String data);

    public String get() {
        return this.data.toString();
    }
}
