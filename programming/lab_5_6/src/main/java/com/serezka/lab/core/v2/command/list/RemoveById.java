package com.serezka.lab.core.v2.command.list;

import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.database.service.TokenService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("remove_by_id_v2")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RemoveById extends Command {
    FlatService flatService;
    TokenService tokenService;

    public RemoveById(FlatService flatService, TokenService tokenService) {
        super("remove_by_id", "command.remove.help");
        this.flatService = flatService;
        this.tokenService = tokenService;
    }

    @Override
    public Response execute(Request request) {
        if (request.getString() == null || request.getString().isBlank() || !request.getString().matches("\\d+"))
            return new Response("command.remove.error.invalidId");

        long id = Long.parseLong(request.getString());
        flatService.removeByIdAndUserId(id, request.getUser().getId());

        return new Response("command.remove.response", id);
    }
}
