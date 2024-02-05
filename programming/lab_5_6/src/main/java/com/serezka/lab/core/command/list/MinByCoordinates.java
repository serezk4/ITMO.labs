package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MinByCoordinates extends Command {
    public MinByCoordinates() {
        super("min_by_coordinates", "min_by_coordinates","вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> userData = bridge.getCurrentData();

        if (userData.isEmpty()) {
            bridge.send("кажется, коллекция пустая. найти необходимый объект не получится.");
            return;
        }

        bridge.addNestedProduct(userData.stream().min(Flat::compareTo).get());
    }
}
