package com.serezka.lab.core.io.socket.server;

public interface ServerWorker extends Runnable, AutoCloseable {
    void init();
}
