package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Reorder extends Command{
    public Reorder() {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("коллекция успешно отсортирована в обратном порядке!");

        chat.getUserData().reorder();
    }
}
