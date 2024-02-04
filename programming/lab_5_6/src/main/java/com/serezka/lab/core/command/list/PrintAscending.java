package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.model.Product;
import com.serezka.lab.core.user.Data;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PrintAscending extends Command {
    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> data = bridge.getData();

        if (data.isEmpty()) {
            bridge.send("кажется, коллекция пустая.");
            return;
        }

        bridge.addNestedProducts(bridge.getData().stream()
                .sorted(Flat::compareTo)
                .collect(Collectors.toCollection(HashSet::new)));
    }
}
