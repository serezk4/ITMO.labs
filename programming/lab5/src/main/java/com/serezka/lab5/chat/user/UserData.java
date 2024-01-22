package com.serezka.lab5.chat.user;

import com.serezka.lab5.chat.obj.Product;

import java.util.LinkedList;

public class UserData extends LinkedList<Product> {
    private static UserData instance = null;

    private UserData() {}

    public static UserData getInstance() {
        if (instance == null) instance = new UserData();
        return instance;
    }
}
