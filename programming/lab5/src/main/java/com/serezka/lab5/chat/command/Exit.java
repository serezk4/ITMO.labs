package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class Exit extends Command{
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("");
        if (!chat.getConsole().get("прощай, сталкер... данные я твои не сохраню, уверен? [y/n]: ").matches("(y)|(Y)|(yes)|(да)|(д)|")) {
            chat.getConsole().send("хорошо, побудем еще в этой консольке некоторое время");
            return;
        }

        chat.getConsole().send("прощай...");
        System.exit(228);
    }
}
