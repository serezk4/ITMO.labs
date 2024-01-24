package com.serezka.lab5.chat.transaction;

import com.serezka.lab5.chat.user.Data;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter @Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    Data data;

    public Transaction(Data data) {
        this.data = data;
    }
}
