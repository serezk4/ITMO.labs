package com.serezka.lab.lab5.hahdler;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import com.serezka.lab.core.v1.handler.Handler;
import com.serezka.lab.core.v1.io.socket.objects.Payload;
import com.serezka.lab.core.v1.io.socket.objects.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("lab5handler")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:chat.properties")
@Log4j2
public class Chat implements Handler<Response, Payload> {
    public static final long USER_ID = -5;

    String helpPattern;

    @Getter
    private List<Command> commands;

    public Chat(@Qualifier("commands") List<Command> commands, @Value("${chat.help.pattern}") String helpPattern) {
        this.commands = commands;
        this.helpPattern = helpPattern;
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
        Bridge commandBridge = new Bridge(USER_ID, payload.getCommand(), payload.getString(), payload.getFlats());
        suitableCommands.getFirst().execute(commandBridge);

        // check internal stack
        commandBridge.getInternalQueries().forEach(this::handle);

        return new Response(commandBridge.getText(), commandBridge.getNestedProducts());
    }

    private String getHelp() {
        return "Все доступные команды: \n" + commands.stream()
                .map(command -> String.format("%n" + helpPattern, command.getSimpleUsage(), command.getHelp()))
                .collect(Collectors.joining());
    }
}
