package com.serezka.lab.core.v2.command.list;

import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("show_v2")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Show extends Command {
    FlatService flatService;

    public Show(FlatService flatService) {
        super("show", "command.show.help");
        this.flatService = flatService;
    }

    @Override
    public Response execute(Request request) {
        return new Response("command.show.response", flatService.findAll());
    }
}
