package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemoveById extends Command {
    FlatService flatService;

    public RemoveById(FlatService flatService) {
        super("remove_by_id {id}","remove_by_id \\d+", "удалить элемент из коллекции по его id");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        // todo

        final long inputId = Long.parseLong(bridge.getInputCommand().split(" ")[1]);
        flatService.removeByIdAndUserId(inputId, bridge.getUserId());
        bridge.send("Элемент с id %d удален", inputId);
    }
}
