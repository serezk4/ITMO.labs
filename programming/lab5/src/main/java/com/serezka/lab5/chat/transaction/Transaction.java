package com.serezka.lab5.chat.transaction;

import com.serezka.lab5.chat.obj.Person;
import com.serezka.lab5.chat.obj.Product;
import com.serezka.lab5.chat.user.UserData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Getter @Setter
    List<Product> userData;

    public Transaction(List<Product> userData) {
        this.userData = userData;
    }
}
