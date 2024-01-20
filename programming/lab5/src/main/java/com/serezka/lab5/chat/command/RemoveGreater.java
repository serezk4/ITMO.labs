package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class RemoveGreater extends Command{
    public RemoveGreater() {
        super("remove_greater \\d+", "удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
