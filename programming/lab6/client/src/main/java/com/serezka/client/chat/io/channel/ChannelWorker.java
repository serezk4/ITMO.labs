package com.serezka.client.chat.io.channel;


import com.serezka.client.chat.handler.Payload;
import com.serezka.client.chat.handler.Response;
import com.serezka.client.chat.object.Product;

import java.net.Socket;
import java.util.Collections;
import java.util.List;

public interface ChannelWorker {
    // # SEND METHODS #

    void send(Payload response);

    default void send(String text) {
        send(new Payload(text, null, null));
    }


    default void send(String command, Product product) {
        send(new Payload(command, product, null));
    }

    default void send(String command, String string) {
        send(new Payload(command, null, string));
    }

    // # GET METHODS #
    Response get();
}
