package com.serezka.client.chat.io.client;

import com.serezka.client.chat.handler.Payload;
import com.serezka.client.chat.handler.Response;
import com.serezka.client.chat.object.Product;

public abstract class ChannelWorker {
    public abstract void connect();
    public abstract void disconnect();
    public abstract void reconnect();

    public abstract void send(Payload payload);

    public void send(String text) {
        send(new Payload(text, null, null));
    }

    public void send(String command, Product product) {
        send(new Payload(command, product, null));
    }

    public void send(String command, String string) {
        send(new Payload(command, null, string));
    }

    public abstract Response get();
}
