package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import org.springframework.stereotype.Component;

@Component
public class UpdateById extends Command{
    public UpdateById() {
        super("update \\d+ \\d+", "обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public void execute(Handler handler, Update update) {

    }
}
