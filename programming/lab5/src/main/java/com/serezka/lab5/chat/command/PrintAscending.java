package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.user.UserData;

public class PrintAscending extends Command {
    public PrintAscending() {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
    }

    @Override
    public void execute(Chat chat, Update update) {
        UserData userData = chat.getUserData();

        if (userData.isEmpty()) {
            chat.getConsole().send("кажется, коллекция пустая.");
            return;
        }

        chat.getUserData().getAscending()
                .forEach(System.out::println);
    }
}
