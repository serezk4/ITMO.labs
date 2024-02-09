package com.serezka.lab.core.io.socket.client;

public interface ClientWorker extends AutoCloseable {
    void init();
    boolean isConnected();
    String getInfo();
    void disconnect();
}
