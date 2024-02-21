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
public class Info extends Command {
    FlatService flatService;

    public Info(FlatService flatService) {
        super("info", "info", "информация о коллекции");
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        final Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());

        bridge.send("тип: %s", collection.getClass());
        bridge.send("хранимые данные: %s", Flat.class.getName());
        bridge.send("размер: %d", collection.size());
    }
}
