package com.serezka.lab5.chat.transaction;

import com.serezka.lab5.chat.user.UserData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter @Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    UserData userData;

    public Transaction(UserData userData) {
        this.userData = userData;
    }
}
