package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Size extends Command {
    public Size() {
        super("size", "size","узнать текущий размер коллекции");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("количество элементов в коллекции: %d", bridge.getCurrentData().size());
    }
}
