package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Value {
    String string;
    Long number;
    List<String> list;
    Map<String, String> map;

    public Value put(Object o) {
        if (o instanceof String) this.string = (String) o;
        if (o instanceof Long) this.number = (Long) o;
        if (o instanceof List) this.list = (List<String>) o;
        if (o instanceof Map) this.map = (Map<String, String>) o;

        if (string == null && number == null && list == null && map == null) throw new IllegalArgumentException(o.toString());

        return this;
    }

    public Map.Entry<Class, Object> getValue() {
        if (string != null) return new AbstractMap.SimpleEntry<>(String.class, string);
        if (number != null) return new AbstractMap.SimpleEntry<>(Long.class, number);
        if (list != null) return new AbstractMap.SimpleEntry<>(List.class, list);
        if (map != null) return new AbstractMap.SimpleEntry<>(Map.class, map);

        throw new IllegalArgumentException();
    }
}
