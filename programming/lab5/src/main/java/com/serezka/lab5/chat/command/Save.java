package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import org.springframework.stereotype.Component;

@Component
public class Save extends Command{
    public Save() {
        super("save", "сохранить коллекцию в файл");
    }

    @Override
    public void execute(Chat chat, Update update) {

    }
}
