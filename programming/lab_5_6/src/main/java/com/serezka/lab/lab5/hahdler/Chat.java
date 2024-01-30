package com.serezka.lab.lab5.hahdler;

import com.serezka.lab.Arts;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.handler.Update;
import com.serezka.lab.core.io.console.ConsoleWorker;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.object.Product;
import com.serezka.lab.core.transaction.TransactionManager;
import com.serezka.lab.core.user.Data;
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
public class Chat implements Handler<String> {
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

        this.data = new Data();
    }


    @Override
    public void run() {
        console.send(Arts.INIT);

        for (;;)
            handle(console.get(inPattern)
                    .replaceAll("\\+gen", formatWorker.writeString(Collections.singletonList(new Product().generate()))));
    }

    public void handle(String input) {
        console.skip();

        if (input.matches("help")) {
            console.send(getHelp());
            console.skip();
            return;
        }

        if (input.matches("cls")) {
            console.clear();
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

        // create bridge
        Bridge commandBridge = new Bridge(new Update(input), getData());
        suitableCommands.getFirst().execute(commandBridge);

        // replace data from bridge
//        getData().replace(commandBridge.getData());

        // print text
        console.send(commandBridge.getText());

        // print products
        commandBridge.getNestedProducts()
                        .stream().map(Product::toString)
                        .forEach(console::send);

        // check internal stack
        commandBridge.getInternalQueries().forEach(this::handle);

//        console.skip();
    }

    private String getHelp() {
        return "Все доступные команды: \n" + commands.stream()
                .map(command -> String.format("%n" + helpPattern, command.getUsage(), command.getHelp()))
                .collect(Collectors.joining());
    }
}
