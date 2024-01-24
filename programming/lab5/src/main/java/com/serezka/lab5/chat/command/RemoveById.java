package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.object.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id \\d+", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(Chat chat, Update update) {
        final int inputId = Integer.parseInt(update.getMessage().split(" ", 2)[1]);

        Optional<Product> filtered = chat.getUserData().stream()
                .filter(product -> product.getId() == inputId)
                .findFirst();

        if (filtered.isEmpty()) {
            chat.getConsole().send("кажется, в коллекции не существует элемента с id %d", inputId);
            return;
        }

        chat.getConsole().send(filtered.get().toString());
    }
}
