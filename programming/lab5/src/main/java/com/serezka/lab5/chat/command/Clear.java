package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Clear extends Command {
    public Clear() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public void execute(Chat chat, Update update) {
        if (!chat.getConsole().get("вы уверены, что хотите удалить все записи из коллекции? [y/n]").matches("(y)|(Y)|(yes)|(да)|(д)|")) {
            chat.getConsole().send("хорошо, данные останутся в покое");
            return;
        }

        chat.getUserData().clear();
        chat.getConsole().send("данные успешно удалены");
    }
}
