package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.user.Data;
import org.springframework.stereotype.Component;

@Component
public class PrintAscending extends Command {
    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public void execute(Chat chat, Update update) {
        Data data = chat.getUserData();

        if (data.isEmpty()) {
            chat.getConsole().send("кажется, коллекция пустая.");
            return;
        }

        chat.getUserData().getAscending().forEach(product -> chat.getConsole().send(product.toString()));
    }
}
