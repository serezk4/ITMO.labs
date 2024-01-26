package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import org.springframework.stereotype.Component;

@Component
public class Reorder extends Command{
    public Reorder() {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        handler.getChannel().send("коллекция успешно отсортирована в обратном порядке!");

        handler.getData().reorder();
    }
}
