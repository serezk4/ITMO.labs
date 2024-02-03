package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Reorder extends Command {
    public Reorder() {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("коллекция успешно отсортирована в обратном порядке!");

        bridge.getData().reorder();
    }
}