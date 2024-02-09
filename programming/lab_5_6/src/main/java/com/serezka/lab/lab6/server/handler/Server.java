package com.serezka.lab.lab6.server.handler;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.objects.State;
import com.serezka.lab.core.io.format.FormatWorker;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("lab6serverHandler")
@Log4j2(topic = "Server")
@ChannelHandler.Sharable
public class Server extends SimpleChannelInboundHandler<Payload> implements Handler<Response, Payload> {
    public static final long USER_ID = -6;

    @Getter List<Command> commands;

    public Server(@Qualifier("commands") List<Command> commands) {
        this.commands = commands;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chx, Payload payload) {
        if (payload == null) {
            log.warn("payload can't be null!");
            return;
        }

        if (payload.getState() == null) {
            log.warn("payload's field 'state' can't be null!");
            return;
        }

        if (payload.getState() == State.CONNECTED) {
            chx.writeAndFlush(Response.connected());
            return;
        }

        log.info("new payload from client: {}", payload.toString());
        Response handledResponse = handle(payload);
        log.info("answer for client: {}", payload.toString());

        chx.writeAndFlush(handledResponse);
    }

    @Override
    public Response handle(Payload payload) {
        List<Command> suitableCommands = commands.stream()
                .filter(command -> payload.getCommand().matches(command.getUsage()))
                .toList();

        if (suitableCommands.isEmpty())
            return new Response("введена некорректная команда, help - все команды");

        if (suitableCommands.size() > 1) log.warn("suitable commands size > 1 ! {}", suitableCommands.toString());

        // create bridge
        Bridge commandBridge = new Bridge(USER_ID, payload.getCommand(), payload.getString(), payload.getFlats());
        suitableCommands.getFirst().execute(commandBridge);

        // check internal stack
        commandBridge.getInternalQueries().forEach(this::handle);

        return new Response(commandBridge.getText(), commandBridge.getNestedProducts());
    }
}

