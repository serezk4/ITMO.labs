package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.user.Data;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class PrintAscending extends Command {
    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public void execute(Bridge bridge) {
        Data data = bridge.getData();

        if (data.isEmpty()) {
            bridge.send("кажется, коллекция пустая.");
            return;
        }

        bridge.addNestedProducts(bridge.getData().getAscending());
    }
}
