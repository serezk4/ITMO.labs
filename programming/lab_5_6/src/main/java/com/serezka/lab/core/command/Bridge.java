package com.serezka.lab.core.command;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.handler.Update;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bridge {
    // input data
    @Getter final Update update;
    @Getter final Set<Flat> data;

    // internal stack
    @Getter final Stack<String> internalQueries = new Stack<>();

    public void addInternalQuery(String internalQuery) {
        this.internalQueries.add(internalQuery);
    }

    public void addInternalQueries(List<String> internalQueries) {
        this.internalQueries.addAll(internalQueries);
    }

    // constructor
    public Bridge(Update update, Set<Flat> data) {
        this.update = update;
        this.data = data;
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
