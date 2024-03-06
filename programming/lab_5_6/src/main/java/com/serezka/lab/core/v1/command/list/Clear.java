package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Clear extends Command {
    FlatService flatService;

    public Clear(FlatService flatService) {
        super("clear", "clear", "очистить коллекцию");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        flatService.removeAllByUserId(bridge.getUserId());
        bridge.send("данные успешно удалены");
    }
}
