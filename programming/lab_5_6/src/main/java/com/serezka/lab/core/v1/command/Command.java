package com.serezka.lab.core.v1.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public abstract class Command {
    String simpleUsage;
    String usage;
    String help;

    public Command(String simpleUsage, String usage, String help) {
        this.simpleUsage = simpleUsage;
        this.usage = usage;
        this.help = help;
    }

    public Command(String simpleUsage, String usage) {
        this.simpleUsage = simpleUsage;
        this.usage = usage;
        this.help = "no help provided";
    }

    public abstract void execute(Bridge bridge);
}
