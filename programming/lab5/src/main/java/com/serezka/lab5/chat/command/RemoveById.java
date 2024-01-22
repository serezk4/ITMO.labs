package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id \\d+", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
