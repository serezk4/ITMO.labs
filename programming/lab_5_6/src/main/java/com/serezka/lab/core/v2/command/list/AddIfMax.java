package com.serezka.lab.core.v2.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.database.service.TokenService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddIfMax extends Command {
    FlatService flatService;
    TokenService tokenService;

    public AddIfMax(FlatService flatService, TokenService tokenService) {
        super("add_if_min", "command.add_if_max.help", 1);

        this.flatService = flatService;
        this.tokenService = tokenService;
    }

    @Override
    public Response execute(Request request) {
        if (request.getFlats() == null || request.getFlats().isEmpty())
            return new Response("command.add_if_max.emptyElements");

        long userId = tokenService.findByToken(request.getToken()).getUser().getId();

        Optional<Flat> minFromCollection = flatService.findAllByUserId(userId)
                .stream().min(Flat::compareTo);

        List<Flat> toRemove = request.getFlats().stream().filter(flat -> minFromCollection.map(value -> value.compareTo(flat) < 0).orElse(true)).toList();
        toRemove.forEach(flat -> flatService.removeByIdAndUserId(flat.getId(), userId));

        return new Response("command.add_if_max.response", toRemove);
    }
}