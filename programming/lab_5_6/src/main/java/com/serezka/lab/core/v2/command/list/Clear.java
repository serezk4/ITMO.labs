package com.serezka.lab.core.v2.command.list;

import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.database.service.TokenService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("clear_v2")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Clear extends Command {
    FlatService flatService;
    TokenService tokenService;

    public Clear(FlatService flatService, TokenService tokenService) {
        super("clear", "command.clear.help");

        this.flatService = flatService;
        this.tokenService = tokenService;
    }

    @Override
    public Response execute(Request request) {
        flatService.removeAllByUserId(request.getUser().getId());
        return new Response("command.clear.response");
    }
}