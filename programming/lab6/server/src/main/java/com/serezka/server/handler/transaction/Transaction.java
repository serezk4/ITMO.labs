package com.serezka.server.handler.transaction;

import com.serezka.server.handler.user.Data;
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
