package com.serezka.lab5.chat.user;

import com.serezka.lab5.chat.obj.Product;

import java.util.LinkedList;
import java.util.List;

public class UserData {
    private static UserData instance = null;

    private List<Product> list;

    private UserData() {
        this.list = new LinkedList<>();
    }

    public static UserData getInstance() {
        if (instance == null) instance = new UserData();
        return instance;
    }
}
