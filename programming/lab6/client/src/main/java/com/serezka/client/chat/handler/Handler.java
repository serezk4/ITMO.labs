package com.serezka.client.chat.handler;

import com.serezka.client.chat.io.channel.ChannelWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class Handler implements Runnable {

    ChannelWorker channelWorker;

    public Handler(@Qualifier("TCPChannelWorker") ChannelWorker channelWorker) {
        this.channelWorker = channelWorker;
    }

    public String handle(Message<?> message) {
        try

        {
//            channelWorker.send(new Update(new Product().generate(), "213"));
        } catch (Exception ex) {
        }
        return "123";
    }

    @Override
    public void run() {

    }
}