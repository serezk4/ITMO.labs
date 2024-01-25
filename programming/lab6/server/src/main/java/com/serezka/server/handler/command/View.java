package com.serezka.server.handler.command;

import com.serezka.server.handler.handler.Handler;
import com.serezka.server.handler.handler.Update;
import com.serezka.server.handler.object.Product;
import org.springframework.stereotype.Component;

@Component
public class View extends Command {
    public View() {
        super("(view)|(show)", "просмотр содержания коллекции");
    }

    @Override
    public void execute(Handler chat, Update update) {
        if (chat.getData().isEmpty()) {
            chat.getChannel().send("Коллекция пуста :(\n\nчтобы добавить элементы воспользуйтесь командами, которые описаны в help");
            return;
        }

        chat.getChannel().send("текущая коллекция:");

        chat.getData()
                .stream().map(Product::toString)
                .forEach(chat.getChannel()::send);
    }
}
