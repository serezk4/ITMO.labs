package com.serezka.server.controller.handler;

import com.serezka.server.controller.object.Person;
import com.serezka.server.controller.object.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Payload implements Serializable {
    String command;

    Product product;
    String string;

    public static Payload empty() {
        return new Payload();
    }
}
