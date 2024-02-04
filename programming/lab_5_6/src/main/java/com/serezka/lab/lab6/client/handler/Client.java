package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:client.properties")
public class Client extends SimpleChannelInboundHandler<Response> implements Handler<Response> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(Payload.builder().command("123").build()).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println(channelFuture.isSuccess());
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("here");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {
        System.out.println("here");
    }

    @Override
    public Response handle(Response input) {
        return null;
    }
}
