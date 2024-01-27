package com.serezka.client.chat.handler;

import com.serezka.client.chat.object.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
public class Payload implements Serializable {
    String command;

    Product product;
    String string;

    public static Payload empty() {
        return new Payload();
    }

    public static Payload connected() {
        return new Payload("connected", null, null);
    }
}
