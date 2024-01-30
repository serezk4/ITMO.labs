package com.serezka.server.controller.handler;

import com.serezka.server.controller.object.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
public class Response {
    State state = null;

    String message = null;
    List<Product> products = null;

    public Response(String message, List<Product> products) {
        this.state = State.OK;
        this.message = message;
        this.products = products;
    }

    public Response(String message) {
        this.message = message;
    }

    public static Response connected() {
        return new Response(State.CONNECTED, null, null);
    }
}
