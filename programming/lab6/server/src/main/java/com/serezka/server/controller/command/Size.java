package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import org.springframework.stereotype.Component;

@Component
public class Size extends Command{
    public Size() {
        super("size", "узнать текущий размер коллекции");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        handler.getChannel().sendf("количество элементов в коллекции: %d",handler.getData().size());
    }
}
