package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveById extends Command {
    public RemoveById() {
        super("remove_by_id \\d+", "удалить элемент из коллекции по его id");
    }

    @Override
    public void execute(Bridge bridge) {
        final int inputId = Integer.parseInt(bridge.getUpdate().getMessage().split(" ", 2)[1]);

        Optional<Flat> filtered = bridge.getData().stream()
                .filter(product -> product.getId() == inputId)
                .findFirst();

        if (filtered.isEmpty()) {
            bridge.send("кажется, в коллекции не существует элемента с id %d", inputId);
            return;
        }

        bridge.addNestedProduct(filtered.get());
    }
}
