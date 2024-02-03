package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.handler.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:client.properties")
public class Client extends SimpleChannelInboundHandler<Response> implements Handler<Response> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {

    }
}
