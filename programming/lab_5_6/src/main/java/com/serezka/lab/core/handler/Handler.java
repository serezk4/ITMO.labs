package com.serezka.lab.core.handler;

public interface Handler<T> extends Runnable {
    default void handle(T input) {}
    default void run() {}
}
