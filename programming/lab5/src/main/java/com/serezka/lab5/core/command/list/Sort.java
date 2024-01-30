package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.object.Product;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Sort extends Command {
    public Sort() {
        super("sort", "отсортировать коллекцию");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.getData().sort(Product::compareTo);
        bridge.send("коллекция успешно отсортирована!");
    }
}
