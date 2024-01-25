package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import org.springframework.stereotype.Component;

@Component
public class Test extends Command{
    public Test() {
        super("ping", "test chat");
    }

    @Override
    public void execute(Handler handler, Update update) {
        handler.getChannel().send("pong");
    }
}
