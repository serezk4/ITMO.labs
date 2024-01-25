package com.serezka.lab5.chat.hahdler;

import com.serezka.lab5.Arts;
import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.io.console.ConsoleWorker;
import com.serezka.lab5.chat.io.format.FormatWorker;
import com.serezka.lab5.chat.object.Product;
import com.serezka.lab5.chat.transaction.TransactionManager;
import com.serezka.lab5.chat.user.Data;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:chat.properties")
@Log4j2
public class Chat implements Runnable {
    String name;
    String outPattern;
    String inPattern;
    String helpPattern;

    @Setter @Getter @NonFinal
    private List<Command> commands = new ArrayList<>();

    @NonFinal @Setter
    Data data;

    FormatWorker formatWorker;

    public Data getData() {
        if (!TransactionManager.isEmpty()) return TransactionManager.get().getData();
        return this.data;
    }

    @Getter
    ConsoleWorker console;

    public Chat(@Value("${chat.name}") String name,
                @Value("${chat.out.pattern}") String outPattern, @Value("${chat.in.pattern}") String inPattern, @Value("${chat.help.pattern}") String helpPattern,
                ConsoleWorker console, FormatWorker formatWorker) {
        this.name = name;

        this.outPattern = outPattern;
        this.inPattern = inPattern;
        this.helpPattern = helpPattern;

        this.console = console;
        this.formatWorker = formatWorker;

        this.data = Data.getInstance();
    }


    @Override
    public void run() {
        console.send(Arts.INIT);

        for (;;)
            execute(console.get(inPattern)
                    .replaceAll("\\+gen", formatWorker.writeString(Collections.singletonList(new Product().generate()))));
    }

    public void execute(String input) {
        console.skip();

        System.out.println(input);

        if (input.matches(".*help.*")) {
            console.send(getHelp());
            console.skip();
            return;
        }

        List<Command> suitableCommands = commands.stream()
                .filter(command -> input.matches(command.getUsage()))
                .toList();

        if (suitableCommands.isEmpty()) {
            console.send("введена некорректная команда, help - все команды");
            console.skip();
            return;
        }

        if (suitableCommands.size() > 1) log.warn("suitable commands size > 1 ! {}", suitableCommands.toString());

        suitableCommands.getFirst().execute(this, new Update(input));
        console.skip();
    }

    private String getHelp() {
        return "Все доступные команды: \n" + commands.stream()
                .map(command -> String.format("%n" + helpPattern, command.getUsage(), command.getHelp()))
                .collect(Collectors.joining());
    }
}
