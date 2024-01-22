package com.serezka.lab5.chat.hahdler;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.console_worker.ConsoleWorker;
import com.serezka.lab5.chat.obj.Product;
import com.serezka.lab5.chat.user.UserData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:chat.properties")
@Log4j2
public class Chat implements Runnable {
    String name;
    String outPattern;
    String inPattern;
    String helpPattern;

    List<Command> commands = new ArrayList<>();
    public void addCommand(Command command) {commands.add(command);}

    @NonFinal
    @Getter @Setter
    List<Product> userData;

    @Getter ConsoleWorker console;

    public Chat(@Value("${chat.name}") String name,
                @Value("${chat.out.pattern}") String outPattern, @Value("${chat.in.pattern}") String inPattern, @Value("${chat.help.pattern}") String helpPattern,
                ConsoleWorker console) {
        this.name = name;

        this.outPattern = outPattern;
        this.inPattern = inPattern;
        this.helpPattern = helpPattern;

        this.console = console;

        this.userData = UserData.getInstance();
    }


    @Override
    public void run() {
        console.send("Chat \"%s\"", name);

        for (;;) {
            console.clear();

            final String input = console.get(inPattern);

            if (input.matches(".*help.*")) {
                console.send(getHelp());
                continue;
            }

            List<Command> suitableCommands = commands.stream().filter(command -> input.matches(command.getUsage())).toList();
            if (suitableCommands.isEmpty()) {
                console.send("введена некорректная команда, help - все команды");
                continue;
            }

            if (suitableCommands.size() > 1) log.warn("suitable commands size > 1 ! {}", suitableCommands.toString());

            suitableCommands.getFirst().execute(this, new Update(input));
        }
    }

    private String getHelp() {
        return commands.stream()
                .map(command -> String.format(helpPattern+"%n", command.getUsage(), command.getHelp()))
                .collect(Collectors.joining());
    }
}
