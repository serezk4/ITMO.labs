package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class View extends Command {
    FlatService flatService;

    public View(FlatService flatService) {
        super("view","view", "просмотр содержания коллекции");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());

        if (collection.isEmpty()) {
            bridge.send("В коллекции отсутствуют элементы...");
            return;
        }

        bridge.send("текущая коллекция:");
        bridge.addNestedProducts(collection);
    }
}
