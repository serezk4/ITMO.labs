package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public abstract class Command {
    String usage;
    String help;

    public Command(String usage, String help) {
        this.usage = usage;
        this.help = help;
    }

    public Command(String usage) {
        this.usage = usage;
        this.help = "no help provided";
    }

    public abstract void execute(Chat chat, Update update);
}
