package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class UpdateById extends Command{
    public UpdateById() {
        super("update \\d+ \\d+", "обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
