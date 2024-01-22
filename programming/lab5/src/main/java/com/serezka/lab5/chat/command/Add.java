package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class Add extends Command{
    public Add() {
        super("add .*", "добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
