package com.serezka.lab.core.v2.command.list;

import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Add extends Command {
    FlatService flatService;

    public Add(FlatService flatService) {
        super("add", "command.add.help");
        this.flatService = flatService;
    }

    @Override
    public Response execute(Request request) {
        if (request.getFlats() == null || request.getFlats().isEmpty())
            return new Response("command.add.error.emptyElements");

        return new Response("command.add.response", flatService.save(request.getFlats().getFirst()));
    }
}
