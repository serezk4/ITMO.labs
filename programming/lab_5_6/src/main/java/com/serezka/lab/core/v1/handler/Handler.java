package com.serezka.lab.core.v1.handler;

public interface Handler<K, V> {
    K handle(V input);
}
