package com.serezka.client.chat.handler;

import com.serezka.client.chat.object.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
public class Response {
    String message;
    List<Product> products = null;

    public Response(String message) {
        this.message = message;
    }

    public static Response empty() {return new Response();}
}
