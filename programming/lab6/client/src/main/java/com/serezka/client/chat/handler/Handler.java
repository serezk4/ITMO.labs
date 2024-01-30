package com.serezka.client.chat.handler;

import com.serezka.client.chat.io.client.ChannelWorker;
import com.serezka.client.chat.io.console.ConsoleWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class Handler implements Runnable {
    ChannelWorker channelWorker;
    ConsoleWorker consoleWorker;

    public Handler(ChannelWorker channelWorker, ConsoleWorker consoleWorker) {
        this.channelWorker = channelWorker;
        this.consoleWorker = consoleWorker;
    }

    public void handle(Response response) {

        System.out.println(response.getMessage());
        final String input = consoleWorker.get(" ~ ");


    }

    @Override
    public void run() {
        channelWorker.send(Payload.connected());

        for (;;) {
            handle(channelWorker.get());
        }
    }
}