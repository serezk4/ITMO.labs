package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.user.Data;
import org.springframework.stereotype.Component;

@Component
public class PrintAscending extends Command {
    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        Data data = handler.getData();

        if (data.isEmpty()) {
            handler.getChannel().send("кажется, коллекция пустая.");
            return;
        }

        handler.getChannel().send(handler.getData().getAscending());
    }
}
