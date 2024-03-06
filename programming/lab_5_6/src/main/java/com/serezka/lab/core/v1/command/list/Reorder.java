package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Reorder extends Command {
    public Reorder() {
        super("reorder","reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("set незя разваренуть todo");
    }
}
