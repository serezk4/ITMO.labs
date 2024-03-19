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
public class Add extends Command {
    String name = "add";
    String help = "commands.add.help";

    FlatService flatService;

    @Override
    public Response execute(Request request, User user) {
        return new Response(flatService.saveAll(request.getFlats()), "commands.add.response");
    }
}
