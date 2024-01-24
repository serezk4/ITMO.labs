package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class RemoveGreater extends Command{
    public RemoveGreater() {
        super("remove_greater \\d+", "удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
