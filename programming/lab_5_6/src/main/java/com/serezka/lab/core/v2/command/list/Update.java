package com.serezka.lab.core.v2.command.list;

import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("update_v2")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Update extends Command {
    FlatService flatService;

    public Update(FlatService flatService) {
        super("update", "command.update.help");
        this.flatService = flatService;
    }

    @Override
    public Response execute(Request request) {
        if (request.getFlats() == null || request.getFlats().isEmpty())
            return new Response("command.update.error.emptyElements");
        if (request.getString() == null || request.getString().isBlank() || !request.getString().matches("\\d+"))
            return new Response("command.update.error.invalidId");

        return new Response("command.update.response", flatService.save(request.getFlats().getFirst()));
    }
}
