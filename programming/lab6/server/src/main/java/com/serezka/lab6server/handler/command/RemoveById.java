package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import com.serezka.lab6server.handler.object.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id \\d+", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(Handler handler, Update update) {
        final int inputId = Integer.parseInt(update.getMessage().split(" ", 2)[1]);

        Optional<Product> filtered = handler.getData().stream()
                .filter(product -> product.getId() == inputId)
                .findFirst();

        if (filtered.isEmpty()) {
            handler.getChannel().send("кажется, в коллекции не существует элемента с id %d", inputId);
            return;
        }

        handler.getChannel().send(filtered.get().toString());
    }
}
