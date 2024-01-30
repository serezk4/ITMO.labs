package com.serezka.lab5.chat.command.list;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Size extends Command {
    public Size() {
        super("size", "узнать текущий размер коллекции");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("количество элементов в коллекции: %d",chat.getData().size());
    }
}
