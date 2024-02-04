package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Sort extends Command {
    public Sort() {
        super("sort", "отсортировать коллекцию");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("коллекция успешно отсортирована! тоже незя todo");
    }
}
