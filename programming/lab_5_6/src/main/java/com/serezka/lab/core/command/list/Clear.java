package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Clear extends Command {
    public Clear() {
        super("clear", "clear", "очистить коллекцию");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.getCurrentData().clear();
        bridge.send("данные успешно удалены");
    }
}
