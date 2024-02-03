package com.serezka.lab.core.io.client;

import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.object.Product;
import io.netty.buffer.ByteBuf;
import io.netty.channel.SimpleChannelInboundHandler;

public interface ClientWorker extends Runnable, AutoCloseable {
    void init();
}
