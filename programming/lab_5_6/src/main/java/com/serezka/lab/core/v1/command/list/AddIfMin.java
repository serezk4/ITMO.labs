package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.repository.FlatRepository;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddIfMin extends Command {
    FlatService flatService;
    public AddIfMin(FlatService flatService,
                    FlatRepository flatRepository) {
        super("add_if_min", "add_if_min", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Optional<Flat> minFromCollection = flatService.findAllByUserId(bridge.getUserId())
                .stream().min(Flat::compareTo);

        bridge.getInputData()
                .stream().filter(flat -> minFromCollection.map(value -> value.compareTo(flat) > 0).orElse(true))
                .map(flatService::save).forEach(bridge::addNestedFlat);

        bridge.send("Добавленные значения:");
    }
}
