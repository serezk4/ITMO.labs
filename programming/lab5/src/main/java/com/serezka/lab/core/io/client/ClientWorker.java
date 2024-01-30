package com.serezka.lab.core.io.client;

import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.object.Product;

public abstract class ClientWorker {
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
