package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import org.springframework.stereotype.Component;

@Component
public class Reorder extends Command{
    public Reorder() {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
    }

    @Override
    public void execute(Handler handler, Update update) {
        handler.getChannel().send("коллекция успешно отсортирована в обратном порядке!");

        handler.getData().reorder();
    }
}
