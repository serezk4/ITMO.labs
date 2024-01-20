package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class MinByCoordinates extends Command{
    public MinByCoordinates() {
        super("min_by_coordinates", "вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
