package com.serezka.lab.core.handler;

import com.serezka.lab.core.io.socket.objects.Response;

public interface Handler<K, V> {
    K handle(V input);
}
