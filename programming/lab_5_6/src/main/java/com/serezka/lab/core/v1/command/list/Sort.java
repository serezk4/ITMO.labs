package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Sort extends Command {
    public Sort() {
        super("sort", "sort","отсортировать коллекцию");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("коллекция успешно отсортирована! тоже незя todo");
    }
}
