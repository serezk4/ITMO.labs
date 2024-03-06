package com.serezka.lab.core.v2.handler;

import com.serezka.lab.core.v2.command.Command;
import com.serezka.lab.core.v1.io.socket.objects.Payload;
import com.serezka.lab.core.v1.io.socket.objects.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class of handler

 * {@link Handler#commands} - list of commands
 * {@link Handler#requests} - deque of requests
 */
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class Handler {
    @Getter List<Command> commands;

    public static volatile Deque<Payload> requests = new ArrayDeque<>();

    /**
     * Handle request and return response
     *
     * @param request - request to handle
     * @return response to request or error message
     */
    public Response handle(Payload request) {
        requests.push(request);

        // validate query
        if (request == null)
            return new Response("handler.error.empty_request");

        if (request.getCommand() == null || request.getCommand().isBlank())
            return new Response("handler.error.empty_command");

        // check if user wants help
        if (request.getCommand().equals("help"))
            return getHelp();

        // find suitable command
        List<Command> suitableCommands = commands.stream()
                .filter(command -> command.getName().equals(request.getCommand()))
                .toList();

        // validate suitable commands
        if (suitableCommands.isEmpty())
            return new Response("handler.error.unknown_command",request.getCommand());
        if (suitableCommands.size() > 1)
            log.warn("WARN! найдено несколько подходящих команд: {}", suitableCommands);

        // execute command
        return suitableCommands.getFirst().execute(request);
    }

    /**
     * Get help message for all commands
     * @return help message
     */
    public Response getHelp() {
        final int maxCommandNameLength = commands.stream()
                .map(Command::getName)
                .mapToInt(String::length).max().orElse(0);

        return new Response(commands.stream()
                .map(command -> String.format("%n * %" + maxCommandNameLength + "s - %s", command.getName(), command.getHelpCode()))
                .collect(Collectors.joining()));
    }
}

