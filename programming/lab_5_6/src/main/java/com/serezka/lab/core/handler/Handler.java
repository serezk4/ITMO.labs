package com.serezka.lab.core.handler;

public interface Handler<T> extends Runnable {
    void handle(T input);
}
