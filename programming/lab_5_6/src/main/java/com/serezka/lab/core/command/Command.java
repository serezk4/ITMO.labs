package com.serezka.lab.core.command;

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

    public abstract void execute(Bridge bridge);
}
