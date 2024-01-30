package com.serezka.lab5.core.command.list;

import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Size extends Command {
    public Size() {
        super("size", "узнать текущий размер коллекции");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("количество элементов в коллекции: %d", bridge.getData().size());
    }
}
