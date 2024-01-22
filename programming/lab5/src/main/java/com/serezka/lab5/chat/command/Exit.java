package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class Exit extends Command{
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("выход из программы без сохранения...");
        System.exit(228);
    }
}
