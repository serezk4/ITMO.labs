package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.object.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterStartsWithPartNumber extends Command {
    public FilterStartsWithPartNumber() {
        super("filter_starts_with_part_number .+", "вывести элементы, значение поля partNumber которых начинается с заданной подстроки");
    }

    @Override
    public void execute(Chat chat, Update update) {
        final String data = update.getMessage().split(" ", 2)[1];

        List<Product> filtered = chat.getData().stream()
                .filter(product -> product.getPartNumber().startsWith(data))
                .toList();

        filtered.forEach(product -> chat.getConsole().send(product.toString()));
    }
}
