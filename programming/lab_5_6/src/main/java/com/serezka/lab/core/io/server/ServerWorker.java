package com.serezka.lab.core.io.server;

public interface ServerWorker extends Runnable, AutoCloseable {
    void init();
}
