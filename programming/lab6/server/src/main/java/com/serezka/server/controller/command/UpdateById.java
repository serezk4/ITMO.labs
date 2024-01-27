package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import org.springframework.stereotype.Component;

@Component
public class UpdateById extends Command{
    public UpdateById() {
        super("update \\d+ \\d+", "обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(Handler handler, Payload payload) {

    }
}
