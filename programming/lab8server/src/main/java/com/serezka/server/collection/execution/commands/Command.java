package com.serezka.server.collection.execution.commands;

import com.serezka.server.authorization.database.model.User;
import com.serezka.server.collection.execution.transfer.Request;
import com.serezka.server.collection.execution.transfer.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
public abstract class Command {
    public abstract String getName();
    public abstract String getHelp();

    public abstract Response execute(Request request, User user);
}
