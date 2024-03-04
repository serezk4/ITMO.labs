package com.serezka.lab.lab7.handler;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.User;
import com.serezka.lab.core.database.service.UserService;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.objects.State;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("lab7handler")
@Log4j2(topic = "Server")
@PropertySource("classpath:chat.properties")
@ChannelHandler.Sharable
public class Server extends SimpleChannelInboundHandler<Payload> implements Handler<Response, Payload> {
    String helpPattern;

    @Getter
    List<Command> commands;

    UserService userService;

    ExecutorService handlingExecutor = Executors.newCachedThreadPool();
    ForkJoinPool answeringPool = new ForkJoinPool();

    public Server(@Qualifier("commands") List<Command> commands, @Value("${chat.help.pattern}") String helpPattern, UserService userService) {
        this.commands = commands;
        this.helpPattern = helpPattern;
        this.userService = userService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext chx, Payload payload) {
        handlingExecutor.submit(() -> checkAndHandle(chx, payload));
    }

    protected void writeAndFlush(ChannelHandlerContext chx, Response response) {
        answeringPool.submit(() -> chx.writeAndFlush(response));
    }

    protected void checkAndHandle(ChannelHandlerContext chx, Payload payload) {
        if (payload == null) {
            log.warn("payload can't be null!");
            writeAndFlush(chx, new Response("payload can't be null!"));
            return;
        }

        if (payload.getUsername() == null || payload.getPassword() == null ||
                payload.getUsername().isBlank()) {
            log.warn("can't parse user with empty params");
            writeAndFlush(chx, new Response("username / password required!"));
            return;
        }

        if (payload.getState() == null) {
            log.warn("payload's field 'state' can't be null!");
            writeAndFlush(chx, new Response("payload's state can't be null!"));
            return;
        }

        if (payload.getState() == State.CONNECTED) {
            writeAndFlush(chx, Response.connected());
            return;
        }

        log.info("new payload from client: {}", payload.toString());
        Response handledResponse = handle(payload);
        log.info("answer for client: {}", handledResponse.toString());

        writeAndFlush(chx, handledResponse);

        log.info("answer for client sent");
    }

    @Override
    public Response handle(Payload payload) {
        if (payload.getCommand() == null)
            return new Response("command can't be null!");

        if (payload.getCommand().equalsIgnoreCase("help"))
            return new Response(getHelp());

        // check authorization
        if (!userService.existsByUsernameAndPassword(payload.getUsername(), payload.getPassword()))
            return new Response("ошибка входа: неправильный логин или пароль");

        User user = userService.findByUsernameAndPassword(payload.getUsername(), payload.getPassword());

        if (payload.getFlats() != null)
            payload.getFlats().forEach(flat -> flat.setUserId(user.getId()));

        // filter commands
        List<Command> suitableCommands = commands.stream()
                .filter(command -> payload.getCommand().matches(command.getUsage()))
                .toList();

        if (suitableCommands.isEmpty())
            return new Response("введена некорректная команда, help - все команды");

        if (suitableCommands.size() > 1) log.warn("suitable commands size > 1 ! {}", suitableCommands.toString());

        // create bridge
        Bridge commandBridge = new Bridge(user.getId(), payload.getCommand(), payload.getString(), payload.getFlats());
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

