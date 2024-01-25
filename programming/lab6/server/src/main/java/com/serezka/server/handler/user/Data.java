package com.serezka.server.handler.user;


import com.serezka.server.handler.object.Product;

import java.util.LinkedList;
import java.util.List;

public class Data extends LinkedList<Product> {
    private static Data instance = null;

    private Data() {}

    public static Data getInstance() {
        if (instance == null) instance = new Data();
        return instance;
    }

    public List<Product> getAscending() {
        return stream()
                .sorted(Product::compareTo)
                .toList();
    }

    public List<Product> removeGreaterThan(Product o) {
        List<Product> toRemove = stream().filter(temp -> temp.compareTo(o) > 0).toList();
        removeAll(toRemove);
        return toRemove;
    }

    public List<Product> reorder() {
        for(int i = 0; i < size()/2; i++) {
            Product buff = get(i);
            set(i, get(size()-i-1));
            set(size()-i-1, buff);
        }

        return this;
    }
}
