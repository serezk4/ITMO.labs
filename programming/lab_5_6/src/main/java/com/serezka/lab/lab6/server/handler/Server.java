package com.serezka.lab.lab6.server.handler;

import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.objects.State;
import com.serezka.lab.core.io.format.FormatWorker;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("lab6serverHandler")
@Log4j2(topic = "Server")
public class Server extends SimpleChannelInboundHandler<Payload> implements Handler<Response, Payload> {
    @Getter
    List<Command> commands;

    @Getter
    FormatWorker<Flat> formatWorker;

    public Server(FormatWorker<Flat> formatWorker, List<Command> commands) {
        this.formatWorker = formatWorker;
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
        Response response = new Response();
        chx.writeAndFlush(response);
    }

    @Override
    public Response handle(Payload input) {
        return null;
    }
}

