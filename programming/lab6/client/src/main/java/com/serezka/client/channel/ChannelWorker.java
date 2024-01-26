package com.serezka.client.channel;


import com.serezka.client.Update;

public interface ChannelWorker extends Runnable {
    void send(String text);

    default void send(String text, Object... args) {
        send(String.format(text, args));
    }

    void send(Update update);
}
