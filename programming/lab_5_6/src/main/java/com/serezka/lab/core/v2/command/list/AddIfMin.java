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

@Component("add_if_min_v2")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddIfMin extends Command {
    FlatService flatService;

    public AddIfMin(FlatService flatService, TokenService tokenService) {
        super("add_if_min", "command.add_if_min.help", 1);

        this.flatService = flatService;
    }

    @Override
    public Response execute(Request request) {
        if (request.getFlats() == null || request.getFlats().isEmpty())
            return new Response("command.add_if_min.emptyElements");

        Optional<Flat> minFromCollection = flatService.findAllByUserId(request.getUser().getId())
                .stream().min(Flat::compareTo);

        List<Flat> toRemove = request.getFlats().stream().filter(flat -> minFromCollection.map(value -> value.compareTo(flat) > 0).orElse(true)).toList();
        toRemove.forEach(flat -> flatService.removeByIdAndUserId(flat.getId(), request.getUser().getId()));

        return new Response("command.add_if_min.response", toRemove);
    }
}
