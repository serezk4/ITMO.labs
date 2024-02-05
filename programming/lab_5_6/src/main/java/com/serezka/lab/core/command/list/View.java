package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class View extends Command {
    public View() {
        super("view","view", "просмотр содержания коллекции");
    }

    @Override
    public void execute(Bridge bridge) {
        if (bridge.getCurrentData().isEmpty()) {
            bridge.send("Коллекция пуста :(\n\nчтобы добавить элементы воспользуйтесь командами, которые описаны в help");
            return;
        }

        bridge.send("текущая коллекция:");

        bridge.addNestedProducts(bridge.getCurrentData());
    }
}
