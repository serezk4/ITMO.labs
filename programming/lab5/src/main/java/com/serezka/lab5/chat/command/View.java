package com.serezka.lab5.chat.command;

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
        chat.getConsole().send("Текущая коллекция:");

        chat.getUserData()
                .stream().map(Product::toString)
                .forEach(chat.getConsole()::send);
    }
}
