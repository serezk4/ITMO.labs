package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.object.Product;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class View extends Command {
    public View() {
        super("(view)|(show)", "просмотр содержания коллекции");
    }

    @Override
    public void execute(Bridge bridge) {
        if (bridge.getData().isEmpty()) {
            bridge.send("Коллекция пуста :(\n\nчтобы добавить элементы воспользуйтесь командами, которые описаны в help");
            return;
        }

        bridge.send("текущая коллекция:");

        bridge.addNestedProducts(bridge.getData());
    }
}
