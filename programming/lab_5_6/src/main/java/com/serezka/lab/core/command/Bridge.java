package com.serezka.lab.core.command;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.socket.objects.Payload;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bridge {
    // const
    @Getter final Long userId;

    // input data
    @Getter final String inputCommand;
    @Getter @Setter String inputText;
    @Getter @Setter Set<Flat> inputData;

    // internal stack
    @Getter final Stack<Payload> internalQueries = new Stack<>();
    public void addInternalQuery(Payload internalQuery) {
        this.internalQueries.add(internalQuery);
    }
    public void addInternalQueries(List<Payload> internalQueries) {
        this.internalQueries.addAll(internalQueries);
    }

    // constructor
    public Bridge(Long userId, String inputCommand, String inputText, Set<Flat> inputData) {
        this.userId = userId;
        this.inputCommand = inputCommand;
        this.inputText = inputText;
        this.inputData = inputData;
    }

    // text
    final StringBuilder builder = new StringBuilder();
    @Getter final Set<Flat> nestedProducts = new HashSet<>();

    public void addNestedProduct(Flat nestedProduct) {
        this.nestedProducts.add(nestedProduct);
    }

    public void addNestedProducts(Set<Flat> nestedProducts) {
        this.nestedProducts.addAll(nestedProducts);
    }

    public void send(String text) {
        this.builder.append(text).append("\n");
    }

    public void send(String pattern, Object... objects) {
        this.send(String.format(pattern, objects));
    }

    public String getText() {
        return this.builder.toString();
    }
}
