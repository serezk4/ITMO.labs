package com.serezka.server.handler.command;

import com.serezka.server.handler.handler.Handler;
import com.serezka.server.handler.handler.Update;
import com.serezka.server.handler.user.Data;
import org.springframework.stereotype.Component;

@Component
public class PrintAscending extends Command {
    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public void execute(Handler handler, Update update) {
        Data data = handler.getData();

        if (data.isEmpty()) {
            handler.getChannel().send("кажется, коллекция пустая.");
            return;
        }

        handler.getData().getAscending().forEach(product -> handler.getChannel().send(product.toString()));
    }
}
