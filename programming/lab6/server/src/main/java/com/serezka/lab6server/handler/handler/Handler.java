package com.serezka.lab6server.handler.handler;

import com.serezka.lab6server.handler.user.Data;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class Handler {
    public Data data = Data.getInstance();

    public String handle(Message<?> message) {
        Update update = (Update) message.getPayload();

        return "hello!";
    }
}
