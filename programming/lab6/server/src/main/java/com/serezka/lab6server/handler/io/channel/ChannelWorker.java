package com.serezka.lab6server.handler.io.channel;

import com.serezka.lab6server.handler.handler.Update;

public interface ChannelWorker {
    void send(String text);

    default void send(String text, Object... args) {
        send(String.format(text, args));
    }

    void send(Update update);
}
