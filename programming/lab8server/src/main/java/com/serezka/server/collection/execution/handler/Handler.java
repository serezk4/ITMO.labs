package com.serezka.server.collection.execution.handler;

import com.serezka.server.collection.execution.commands.Command;
import com.serezka.server.collection.execution.transfer.Request;
import com.serezka.server.collection.execution.transfer.Response;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log4j2
public class Handler {
    final List<Command> commands;

    public Handler(@Qualifier("commands") List<Command> commands) {
        this.commands = commands;
    }

    /**
     * Handle request and return response
     *
     * @param request - request to handle
     * @return response to request or error message
     */
    public Response handle(Request request) {
        // validate query
        if (request == null)
            return new Response("handler.error.request.null");

        log.info("new request: {}", request.toString());

        if (StringUtils.isBlank(request.getCommand()))
            return new Response("handler.error.request.command.empty");

        // check if user wants help
        if ("help".equalsIgnoreCase(request.getCommand()))
            return getHelp();

        // find suitable command
        Optional<Command> suitableCommand = commands.stream()
                .filter(command -> command.getName().equals(request.getCommand()))
                .findFirst();

        if (suitableCommand.isEmpty())
            return new Response("handler.error.request.command.unknown");

        return suitableCommand.get().execute(request);
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
                .map(command -> String.format("%n * %" + maxCommandNameLength + "s - %s", command.getName(), command.getHelp()))
                .collect(Collectors.joining()));
    }
}
