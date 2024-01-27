package com.serezka.server.controller.handler;

import com.serezka.server.controller.command.Command;
import com.serezka.server.controller.io.channel.ChannelWorker;
import com.serezka.server.controller.io.format.FormatWorker;
import com.serezka.server.controller.transaction.TransactionManager;
import com.serezka.server.controller.user.Data;
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
public class Handler implements Runnable {
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
    ChannelWorker channel;

    public Handler(FormatWorker formatWorker, ChannelWorker channel,
                   List<Command> commands) {
        this.formatWorker = formatWorker;
        this.channel = channel;

        this.commands = commands;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("starting handling...");

        for (; ; ) {
            if (!channel.isConnected()) {
                log.info("waiting...");
                Thread.sleep(1000);
                continue;
            }

            handle();
        }
    }


    public void handle() {
        System.out.println(channel.get().getCommand());
        if (true) System.exit(1);
    }
}

