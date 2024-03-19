package com.serezka.server.collection.execution.commands.list;

import com.serezka.server.authorization.database.model.User;
import com.serezka.server.collection.database.service.FlatService;
import com.serezka.server.collection.execution.commands.Command;
import com.serezka.server.collection.execution.transfer.Request;
import com.serezka.server.collection.execution.transfer.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class Clear extends Command {
    String name = "clear";
    String help = "commands.clear.help";

    FlatService flatService;

    @Override
    public Response execute(Request request, User user) {
        flatService.removeAllByUserId(user.getId());
        return new Response("commands.clear.response");
    }
}
