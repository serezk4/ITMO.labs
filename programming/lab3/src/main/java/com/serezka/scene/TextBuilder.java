package com.serezka.scene;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public final class TextBuilder {
    List<String> messages = new ArrayList<>();

    private TextBuilder() {}

    private static TextBuilder instance = null;

    public static TextBuilder getInstance() {
        if (instance == null) instance = new TextBuilder();
        return instance;
    }

    public TextBuilder add(String... text) {
        Collections.addAll(messages, text);
        return this;
    }

    public String getResult() {
        return messages.stream()
                .map(part -> String.join(" ",".?!,".contains(part) ? part : " " + part ))
                .collect(Collectors.joining())
                .replaceAll(" {2}", " ");
    }

    @Override
    public String
    toString() {
        return "TextBuilder{" +
                "messages=" + messages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextBuilder that = (TextBuilder) o;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }
}
