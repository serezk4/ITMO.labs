package com.serezka.server.collection.execution.commands;

import com.serezka.server.collection.execution.transfer.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
@Getter
public abstract class Command {
    String name;
    String help;

    public abstract Response execute(Request request);
}
