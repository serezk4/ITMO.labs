package com.serezka.lab5.chat.command.list;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Exit extends Command {
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("");

        if (!chat.getConsole().get("прощай, сталкер... данные я твои не сохраню, уверен? [y/n]: ").matches("(y)|(Y)|(yes)|(да)|(д)|")) {
            chat.getConsole().send("программа продолжит работу");
            return;
        }

        chat.getConsole().send("бб");
        System.exit(228);
    }
}
