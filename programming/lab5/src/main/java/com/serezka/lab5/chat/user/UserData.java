package com.serezka.lab5.chat.user;

import com.serezka.lab5.chat.obj.Product;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UserData extends LinkedList<Product> {
    private static UserData instance = null;

    private UserData() {}

    public static UserData getInstance() {
        if (instance == null) instance = new UserData();
        return instance;
    }

    public List<Product> getAscending() {
        return stream()
                .sorted(Product::compareTo)
                .toList();
    }
}
