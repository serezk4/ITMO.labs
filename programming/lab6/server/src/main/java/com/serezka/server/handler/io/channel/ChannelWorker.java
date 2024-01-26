package com.serezka.server.handler.io.channel;

import com.serezka.server.handler.handler.Update;

import java.net.Socket;

public interface ChannelWorker extends Runnable {
    Socket acceptClient();

    void send(String text);

    default void send(String text, Object... args) {
        send(String.format(text, args));
    }

    void send(Update update);
}
