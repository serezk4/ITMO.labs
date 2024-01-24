package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Test extends Command{
    public Test() {
        super("ping", "test chat");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("pong");
    }
}
