package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.object.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id \\d+", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        if (payload.getString() == null || payload.getString().isBlank() || !payload.getString().matches("\\d+")) {
            handler.getChannel().send("Ошибка! Строка или пуста, или введена неверно.");
            return;
        }

        final int inputId = Integer.parseInt(payload.getString());

        Optional<Product> filtered = handler.getData().stream()
                .filter(product -> product.getId() == inputId)
                .findFirst();

        if (filtered.isEmpty()) {
            handler.getChannel().sendf("кажется, в коллекции не существует элемента с id %d", inputId);
            return;
        }

        handler.getChannel().send(filtered.get());
    }
}
