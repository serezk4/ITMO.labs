package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
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
