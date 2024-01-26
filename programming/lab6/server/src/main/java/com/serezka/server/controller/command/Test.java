package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import org.springframework.stereotype.Component;

@Component
public class Test extends Command{
    public Test() {
        super("ping", "test chat");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        handler.getChannel().send("pong");
    }
}
