package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class MinByCoordinates extends Command {
    FlatService flatService;

    public MinByCoordinates(FlatService flatService) {
        super("min_by_coordinates", "min_by_coordinates","вывести любой объект из коллекции, значение поля coordinates которого является минимальным");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());

        if (collection.isEmpty()) {
            bridge.send("кажется, коллекция пустая. найти необходимый объект не получится.");
            return;
        }

        bridge.addNestedFlat(collection.stream().min(Flat::compareTo).get());
    }
}
