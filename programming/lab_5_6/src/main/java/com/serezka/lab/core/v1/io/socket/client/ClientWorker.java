package com.serezka.lab.core.v1.io.socket.client;

public interface ClientWorker extends AutoCloseable {
    void init();
    boolean isConnected();
    String getInfo();
    void disconnect();
}
