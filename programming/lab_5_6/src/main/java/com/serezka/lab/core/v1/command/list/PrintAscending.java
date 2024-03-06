package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrintAscending extends Command {
    FlatService flatService;

    public PrintAscending(FlatService flatService) {
        super("print_ascending", "print_ascending","вывести элементы коллекции в порядке возрастания");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());

        if (collection.isEmpty()) {
            bridge.send("кажется, коллекция пустая.");
            return;
        }

        bridge.addNestedFlats(collection.stream()
                .sorted(Flat::compareTo)
                .collect(Collectors.toCollection(HashSet::new)));
    }
}
