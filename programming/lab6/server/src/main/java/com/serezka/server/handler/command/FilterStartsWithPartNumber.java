package com.serezka.server.handler.command;

import com.serezka.server.handler.handler.Handler;
import com.serezka.server.handler.handler.Update;
import com.serezka.server.handler.object.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterStartsWithPartNumber extends Command {
    public FilterStartsWithPartNumber() {
        super("filter_starts_with_part_number .+", "вывести элементы, значение поля partNumber которых начинается с заданной подстроки");
    }

    @Override
    public void execute(Handler handler, Update update) {
        final String data = update.getMessage().split(" ", 2)[1];

        List<Product> filtered = handler.getData().stream()
                .filter(product -> product.getPartNumber().startsWith(data))
                .toList();

        filtered.forEach(product -> handler.getChannel().send(product.toString()));
    }
}
