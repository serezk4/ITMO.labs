package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class UpdateById extends Command {
    public UpdateById() {
        super("update \\d+ \\d+", "обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(Bridge bridge) {

    }
}
