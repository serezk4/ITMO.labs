package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MinByTransport extends Command {
    FlatService flatService;

    public MinByTransport(FlatService flatService) {
        super("min_by_transport", "min_by_transport", "вывести любой объект из коллекции, значение поля transport которого является минимальным");
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Optional<Flat> minFromCollection = flatService.findAllByUserId(bridge.getUserId())
                .stream().min(Comparator.comparing(Flat::getTransport));

        if (minFromCollection.isPresent()) {
            bridge.send("Результат:");
            bridge.addNestedFlat(minFromCollection.get());
            return;
        }

        bridge.send("Кажется, коллекция пуста и не получится найти минимальный элемент.");
    }
}
