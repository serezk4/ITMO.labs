package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.object.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterStartsWithPartNumber extends Command {
    public FilterStartsWithPartNumber() {
        super("filter_starts_with_part_number .+", "вывести элементы, значение поля partNumber которых начинается с заданной подстроки");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        if (payload.getString() == null) {
            handler.getChannel().send("Ошибка! Нету данных о продукте!");
            return;
        }

        List<Product> filtered = handler.getData().stream()
                .filter(product -> product.getPartNumber().startsWith(payload.getString()))
                .toList();

        handler.getChannel().send(filtered);
    }
}
