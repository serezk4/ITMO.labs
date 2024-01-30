package com.serezka.lab5.chat.command.list;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Cls extends Command {
    public Cls() {
        super("cls", "очистить консоль");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().clear();
    }
}
