package com.serezka.lab.core.handler;

import com.serezka.lab.core.io.socket.objects.Response;

public interface Handler<T> {
    Response handle(T input);
}
