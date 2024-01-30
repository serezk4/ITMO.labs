package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.io.client.ClientWorker;
import com.serezka.lab.core.io.console.ConsoleWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class Client implements Handler<Response> {
    ClientWorker channelWorker;
    ConsoleWorker consoleWorker;

    public Client(ClientWorker channelWorker, ConsoleWorker consoleWorker) {
        this.channelWorker = channelWorker;
        this.consoleWorker = consoleWorker;
    }

    public void handle(Response response) {
        System.out.println(response.getMessage());
        final String input = consoleWorker.get(" ~ ");


    }

    @Override
    public void run() {
        for (; ; ) {
            if (!channelWorker.isConnected()) {
                channelWorker.connect();
                channelWorker.send(Payload.connected());
            }

            handle(channelWorker.get());
        }
    }
}