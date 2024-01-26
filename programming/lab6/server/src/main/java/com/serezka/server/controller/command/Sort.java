package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.object.Product;
import org.springframework.stereotype.Component;

@Component
public class Sort extends Command{
    public Sort() {
        super("sort", "отсортировать коллекцию");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        handler.getData().sort(Product::compareTo);
        handler.getChannel().send("коллекция успешно отсортирована!");
    }
}
