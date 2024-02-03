package com.serezka.lab.core.io.socket.client;

public interface ClientWorker extends Runnable, AutoCloseable {
    void init();
}
