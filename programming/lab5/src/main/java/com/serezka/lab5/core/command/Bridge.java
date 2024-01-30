package com.serezka.lab5.core.command;

import com.serezka.lab5.core.handler.Update;
import com.serezka.lab5.core.object.Product;
import com.serezka.lab5.core.user.Data;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bridge {
    // input data
    @Getter final Update update;
    @Getter @Setter Data data;

    // internal stack
    @Getter final Stack<String> internalQueries = new Stack<>();

    public void addInternalQuery(String internalQuery) {
        this.internalQueries.add(internalQuery);
    }

    public void addInternalQueries(List<String> internalQueries) {
        this.internalQueries.addAll(internalQueries);
    }

    // constructor
    public Bridge(Update update, Data data) {
        this.update = update;
        this.data = data;
    }

    // text
    final StringBuilder builder = new StringBuilder();
    @Getter final List<Product> nestedProducts = new ArrayList<>();

    public void addNestedProduct(Product nestedProduct) {
        this.nestedProducts.add(nestedProduct);
    }

    public void addNestedProducts(List<Product> nestedProducts) {
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
