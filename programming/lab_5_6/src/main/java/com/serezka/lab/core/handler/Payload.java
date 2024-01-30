package com.serezka.lab.core.handler;

import com.serezka.lab.core.object.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
public class Payload implements Serializable {
    State state = null;
    String command = null;

    Product product = null;
    String string = null;

    public Payload(String command, Product product, String string) {
        this.command = command;
        this.product = product;
        this.string = string;
    }

    public static Payload empty() {
        return new Payload();
    }

    public static Payload connected() {
        return new Payload(State.CONNECTED,null, null, null);
    }
}