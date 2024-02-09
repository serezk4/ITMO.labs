package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.objects.State;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("lab6client")
@PropertySource("classpath:client.properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client extends SimpleChannelInboundHandler<Response> implements Handler<Response, Payload> {
    ChannelHandlerContext context = null;

    @Override
    public void channelActive(ChannelHandlerContext context) {
        this.context = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {
        System.out.println(response.toString());
    }

    @Override
    public Response handle(Payload input) {
        context.writeAndFlush(input);
        return Response.builder().state(State.OK).message("waiting...").build();
    }
}
