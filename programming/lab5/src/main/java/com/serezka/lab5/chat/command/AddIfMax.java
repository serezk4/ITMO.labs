package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class AddIfMax extends Command{
    public AddIfMax() { // todo
        super("add_if_max \\d+", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
