package com.serezka.client.chat.handler;

import com.serezka.client.chat.io.channel.ChannelWorker;
import com.serezka.client.chat.io.console.ConsoleWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
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

    public String handle(Response response) {
        return "123";
    }

    @Override
    public void run() {
        for (;;) {
            channelWorker.send(Payload.connected());
            handle(channelWorker.get());
        }
    }
}