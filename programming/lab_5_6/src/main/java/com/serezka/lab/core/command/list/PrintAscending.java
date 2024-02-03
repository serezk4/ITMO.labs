package com.serezka.lab.core.command.list;

import com.serezka.lab.core.user.Data;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
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