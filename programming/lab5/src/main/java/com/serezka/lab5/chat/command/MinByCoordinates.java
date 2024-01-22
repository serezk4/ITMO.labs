package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.obj.Person;
import com.serezka.lab5.chat.obj.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MinByCoordinates extends Command{
    public MinByCoordinates() {
        super("min_by_coordinates", "вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    @Override
    public void execute(Chat chat, Update update) {
        List<Product> userData = chat.getUserData();

        if (userData.isEmpty()) {
            chat.getConsole().send("кажется, коллекция пустая. найти необходимый объект не получится.");
            return;
        }

        Product minimalByCoordinates = userData.stream()
                .min(Comparator.comparingDouble(product -> product.getCoordinates().sum())).get();




    }
}
