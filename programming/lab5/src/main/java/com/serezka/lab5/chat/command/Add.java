package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.transaction.TransactionManager;

import java.util.List;

public class Add extends Command{
    public Add() {
        super("add \\d+", "добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
