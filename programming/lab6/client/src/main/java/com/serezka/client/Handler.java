package com.serezka.client;

import com.serezka.client.channel.ChannelWorker;
import com.serezka.client.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.Message;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Handler {

    ChannelWorker channelWorker;

    public Handler(ChannelWorker channelWorker) {
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
}