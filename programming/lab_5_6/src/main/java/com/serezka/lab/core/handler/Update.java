package com.serezka.lab.core.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class Update {
    String message;

    public Update(String message) {
        this.message = message;
    }
}
