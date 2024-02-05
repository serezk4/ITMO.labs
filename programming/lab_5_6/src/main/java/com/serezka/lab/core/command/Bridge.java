package com.serezka.lab.core.command;

import com.serezka.lab.core.database.model.Flat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bridge {
    // input data
    @Getter final String inputCommand;
    @Getter final Set<Flat> inputData;

    @Getter final Set<Flat> currentData;

    // internal stack
    @Getter final Stack<String> internalQueries = new Stack<>();
    public void addInternalQuery(String internalQuery) {
        this.internalQueries.add(internalQuery);
    }
    public void addInternalQueries(List<String> internalQueries) {
        this.internalQueries.addAll(internalQueries);
    }

    // constructor
    public Bridge(String inputCommand, Set<Flat> inputData, Set<Flat> currentData) {
        this.inputCommand = inputCommand;
        this.inputData = inputData;
        this.currentData = currentData;
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
