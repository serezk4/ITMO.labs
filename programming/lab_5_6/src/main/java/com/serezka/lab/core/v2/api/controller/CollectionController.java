package com.serezka.lab.core.v2.api.controller;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.model.User;
import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.database.service.TokenService;
import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import com.serezka.lab.core.v2.handler.Handler;
import com.serezka.lab.core.v2.localization.Localization;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/collection")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CollectionController {
    FlatService flatService;
    TokenService tokenService;

    Localization localization = Localization.getInstance();
    Handler handler;

    public User validateToken(String token) {
        return tokenService.findByToken(token).getUser();
    }

    public Response localize(Response response, User user) {
        if (response.getCode() != null) response.setCode(String.format(localization.get(response.getCode(), user), response.getFormatData()));
        return response;
    }

//    @GetMapping("/error")
//    public String error() {
//        return "tes";
//    } todo

    @GetMapping("/execute")
    public Response execute(@RequestParam(value = "token", required = true) String token,
                            @RequestParam(value = "command", required = true) String command,
                            @RequestParam(value = "string", required = false) String string,
                            @RequestBody(required = false) List<Flat> flats) {
        User user = validateToken(token);
        if (user == null)
            return new Response("invalid token");

        return localize(handler.handle(new Request(user, command, flats, string)), user);
    }

    @GetMapping("/list")
    public Response list(@RequestParam(value = "token", required = true) String token) {
        User user = validateToken(token);
        if (user == null)
            return new Response("invalid token");

        return localize(new Response(flatService.findAll()), user);
    }

    @GetMapping("/size")
    public Response size(@RequestParam(value = "token", required = true) String token) {
        User user = validateToken(token);
        if (user == null)
            return new Response("invalid token");

        return localize(new Response("command.size.response", flatService.count()), user);
    }
}
