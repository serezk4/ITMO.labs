package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateById extends Command {
    FlatService flatService;

    public UpdateById(FlatService flatService) {
        super("update","update", "обновить значение элемента коллекции, id которого равен заданному");
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.getInputData().stream().map(flatService::save).forEach(bridge::addNestedFlat);
        bridge.send("Измененные значения");
    }
}
