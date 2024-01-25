package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import org.springframework.stereotype.Component;

@Component
public class Clear extends Command {
    public Clear() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public void execute(Handler handler, Update update) {
        handler.getData().clear();
        handler.getChannel().send("данные успешно удалены");
    }
}
