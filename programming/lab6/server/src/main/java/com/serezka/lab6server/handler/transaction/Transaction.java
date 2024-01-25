package com.serezka.lab6server.handler.transaction;

import com.serezka.lab6server.handler.user.Data;
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
