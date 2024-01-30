package com.serezka.lab5.chat.command.list;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.object.Product;
import org.springframework.stereotype.Component;

@Component
public class Sort extends Command {
    public Sort() {
        super("sort", "отсортировать коллекцию");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getData().sort(Product::compareTo);
        chat.getConsole().send("коллекция успешно отсортирована!");
    }
}
