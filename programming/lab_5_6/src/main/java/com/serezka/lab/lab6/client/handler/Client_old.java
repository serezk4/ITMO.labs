package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.io.client.ClientWorker;
import com.serezka.lab.core.io.console.ConsoleWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Log4j2
@Deprecated
public class Client_old implements Handler<Response> {
    ClientWorker channelWorker;
    ConsoleWorker consoleWorker;

    public Client_old(ClientWorker channelWorker, ConsoleWorker consoleWorker) {
        this.channelWorker = channelWorker;
        this.consoleWorker = consoleWorker;
    }

    public void handle(Response response) {
        channelWorker.send("help");
    }

    @Override
    public void run() {
        for (;;) {
            if (!channelWorker.isConnected()) {
                log.info("waiting for server...");
                channelWorker.connect();
                continue;
            }

            channelWorker.send("help");
            handle(channelWorker.get());
        }
    }
}