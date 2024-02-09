package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.objects.State;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("lab6client")
@PropertySource("classpath:client.properties")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2(topic = "Client")
@ChannelHandler.Sharable
public class Lab6ClientHandler extends SimpleChannelInboundHandler<Response> implements Handler<Response, Payload> {
    @Getter List<Command> commands;

    @NonFinal ChannelHandlerContext context = null;

    public Lab6ClientHandler(@Qualifier("commands") List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void channelActive(@NonNull ChannelHandlerContext context) {
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
