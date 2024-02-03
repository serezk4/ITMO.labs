package com.serezka.lab.core.io.socket.objects;

import com.serezka.lab.core.database.model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
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