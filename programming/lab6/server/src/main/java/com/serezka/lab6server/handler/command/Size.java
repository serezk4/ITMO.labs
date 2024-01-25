package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import org.springframework.stereotype.Component;

@Component
public class Size extends Command{
    public Size() {
        super("size", "узнать текущий размер коллекции");
    }

    @Override
    public void execute(Handler handler, Update update) {
        handler.getChannel().send("количество элементов в коллекции: %d",handler.getData().size());
    }
}
