package com.serezka.lab.lab5.hahdler;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:chat.properties")
@Log4j2
public class Chat implements Handler<Payload> {
    public static final long USER_ID = -5;

    String helpPattern;

    @Getter
    private List<Command> commands;

    FlatService flatService;

    public Chat(List<Command> commands, @Value("${chat.help.pattern}") String helpPattern,
                FlatService flatService) {
        this.commands = commands;
        this.helpPattern = helpPattern;
        this.flatService = flatService;
    }

    @Override
    public Response handle(final Payload payload) {
        if (payload.getCommand().matches("help"))
            return new Response(getHelp());

        List<Command> suitableCommands = commands.stream()
                .filter(command -> payload.getCommand().matches(command.getUsage()))
                .toList();

        if (suitableCommands.isEmpty())
            return new Response("введена некорректная команда, help - все команды");

        if (suitableCommands.size() > 1) log.warn("suitable commands size > 1 ! {}", suitableCommands.toString());

        // create bridge
        Bridge commandBridge = new Bridge(payload.getCommand(), payload.getString(), payload.getFlats(), flatService.findAllByUserId(USER_ID));
        suitableCommands.getFirst().execute(commandBridge);

        // check internal stack
        commandBridge.getInternalQueries().forEach(this::handle);

        return new Response(commandBridge.getText(), commandBridge.getNestedProducts());
    }

    private String getHelp() {
        return "Все доступные команды: \n" + commands.stream()
                .map(command -> String.format("%n" + helpPattern, command.getUsage(), command.getHelp()))
                .collect(Collectors.joining());
    }
}
