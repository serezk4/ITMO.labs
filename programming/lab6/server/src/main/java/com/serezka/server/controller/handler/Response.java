package com.serezka.server.controller.handler;

import com.serezka.server.controller.object.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor
public class Response {
    String message;
    List<Product> products = null;

    public Response(String message) {
        this.message = message;
    }
}
