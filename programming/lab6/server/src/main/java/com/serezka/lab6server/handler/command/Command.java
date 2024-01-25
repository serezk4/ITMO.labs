package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

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

    public abstract void execute(Handler handler, Update update);
}
