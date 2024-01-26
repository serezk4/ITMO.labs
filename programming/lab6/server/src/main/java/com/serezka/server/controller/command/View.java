package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.object.Product;
import org.springframework.stereotype.Component;

@Component
public class View extends Command {
    public View() {
        super("(view)|(show)", "просмотр содержания коллекции");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        if (handler.getData().isEmpty()) {
            handler.getChannel().send("Коллекция пуста :(\n\nчтобы добавить элементы воспользуйтесь командами, которые описаны в help");
            return;
        }


        handler.getChannel().send("текущая коллекция: ", handler.getData().stream().toList());
    }
}
