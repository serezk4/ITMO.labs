package com.serezka.lab6server.handler.io.channel;

import com.serezka.lab6server.handler.handler.Update;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TCPChannelWorker implements ChannelWorker{
    MessageChannel messageChannel;

    public TCPChannelWorker(@Qualifier("outboundChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    @Override
    public void send(String text) {
        messageChannel.send(MessageBuilder.withPayload(new Update(text)).build());
    }

    @Override
    public void send(Update update) {
        messageChannel.send(MessageBuilder.withPayload(update).build());
    }
}
