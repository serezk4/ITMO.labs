package com.serezka.lab5.chat.command.list;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.object.Product;
import org.springframework.stereotype.Component;

@Component
public class View extends Command {
    public View() {
        super("(view)|(show)", "просмотр содержания коллекции");
    }

    @Override
    public void execute(Chat chat, Update update) {
        if (chat.getData().isEmpty()) {
            chat.getConsole().send("Коллекция пуста :(\n\nчтобы добавить элементы воспользуйтесь командами, которые описаны в help");
            return;
        }

        chat.getConsole().send("текущая коллекция:");

        chat.getData()
                .stream().map(Product::toString)
                .forEach(chat.getConsole()::send);
    }
}
