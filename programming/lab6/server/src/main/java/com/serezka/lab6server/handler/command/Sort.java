package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import com.serezka.lab6server.handler.object.Product;
import org.springframework.stereotype.Component;

@Component
public class Sort extends Command{
    public Sort() {
        super("sort", "отсортировать коллекцию");
    }

    @Override
    public void execute(Handler handler, Update update) {
        handler.getData().sort(Product::compareTo);
        handler.getChannel().send("коллекция успешно отсортирована!");
    }
}
