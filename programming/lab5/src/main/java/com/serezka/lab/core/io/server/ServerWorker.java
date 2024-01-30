package com.serezka.lab.core.io.server;

import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.object.Product;

import java.net.Socket;
import java.util.Collections;
import java.util.List;

public interface ServerWorker {
    Socket acceptClient();
    boolean isConnected();

    // # SEND METHODS #

    void send(Response response);

    default void send(String text) {
        send(new Response(text));
    }

    default void send(String text, List<Product> products) {
        send(new Response(text, products));
    }

    default void send(String text, Product product) {
        send(new Response(text, Collections.singletonList(product)));
    }

    default void send(List<Product> products) {
        send("Результат", products);
    }

    default void send(Product product) {
        send("Результат", Collections.singletonList(product));
    }

    default void sendf(String text, Object... args) {
        send(String.format(text, args));
    }

    // # GET METHODS #
    Payload get();
}
