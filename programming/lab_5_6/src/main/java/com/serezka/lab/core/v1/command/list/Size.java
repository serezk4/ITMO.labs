package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Size extends Command {
    FlatService flatService;

    public Size(FlatService flatService) {
        super("size", "size","узнать текущий размер коллекции");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("количество элементов в коллекции: %d", flatService.countAllByUserId(bridge.getUserId()));
    }
}
