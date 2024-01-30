package com.serezka.lab.lab6.server.handler;

import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.handler.State;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.io.server.ServerWorker;
import com.serezka.lab.core.transaction.TransactionManager;
import com.serezka.lab.core.user.Data;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class Server implements Handler<Payload> {
    @Getter
    List<Command> commands;

    @NonFinal
    @Setter
    Data data;

    public Data getData() {
        if (!TransactionManager.isEmpty()) return TransactionManager.get().getData();
        return this.data;
    }

    @Getter
    FormatWorker formatWorker;

    @Getter
    ServerWorker channel;

    public Server(FormatWorker formatWorker, ServerWorker channel,
                  List<Command> commands) {
        this.formatWorker = formatWorker;
        this.channel = channel;

        this.commands = commands;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("starting handling...");

        for (;;) {
            if (!channel.isConnected()) {
                log.info("waiting for client...");
                continue;
            }

            handle(channel.get());
        }
    }


    public void handle(Payload payload) {
        if (payload == null) {
            log.warn("payload can't be null!");
            return;
        }

        if (payload.getState() == null) {
            log.warn("payload's field 'state' can't be null!");
            return;
        }

        if (payload.getState() == State.CONNECTED) {
            channel.send(Response.connected());
            return;
        }

        log.info("new payload from client: {}", payload.toString());
    }
}

