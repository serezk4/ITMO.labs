package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemoveLower extends Command {
    FlatService flatService;

    public RemoveLower(FlatService flatService) {
        super("remove_lower", "remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        if (bridge.getInputData() == null || bridge.getInputData().isEmpty()) {
            bridge.send("вы не ввели значения!");
            return;
        }

        Flat minInput = Collections.min(bridge.getInputData());

        flatService.findAllByUserId(bridge.getUserId())
                .stream().filter(flat -> minInput.compareTo(flat) > 0)
                .forEach(flat -> {
                    flatService.removeByIdAndUserId(flat.getId(), bridge.getUserId());
                    bridge.addNestedFlat(flat);
                });

        bridge.send("Удаленные записи:");

    }
}
