package com.serezka.lab.lab5.web;

import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.format.FormatWorker;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/lab5")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ComponentScan("com.serezka.lab.core.command")
@Log4j2
public class ChatController {
    String name;
    String outPattern;
    String inPattern;
    String helpPattern;

    @Setter
    @Getter
    @NonFinal
    private List<Command> commands = new ArrayList<>();

    FormatWorker formatWorker;

    public ChatController(List<Command> commands, @Value("${chat.name}") String name,
                          @Value("${chat.out.pattern}") String outPattern, @Value("${chat.in.pattern}") String inPattern, @Value("${chat.help.pattern}") String helpPattern,
                          @Qualifier("csvFormatWorker") FormatWorker formatWorker) {
        this.name = name;

        this.commands = commands;

        this.outPattern = outPattern;
        this.inPattern = inPattern;
        this.helpPattern = helpPattern;

        this.formatWorker = formatWorker;
    }

    @GetMapping("/")
    public String chatPage() {
        return "lab5";
    }

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestParam("message") String message) {
        final String input = message.replaceAll("-g", formatWorker.writeString(Set.of(Flat.generate())));

        if (input.matches("help"))
            return new Message(getHelp());

        List<Command> suitableCommands = commands.stream()
                .filter(command -> input.matches(command.getUsage()))
                .toList();

        if (suitableCommands.isEmpty())
            return new Message("введена некорректная команда, help - все команды");

        if (suitableCommands.size() > 1)
            log.warn("suitable commands size > 1 ! {}", suitableCommands.toString());

        // create bridge
       /* Bridge commandBridge = new Bridge(new Update(input), getData());
        suitableCommands.getFirst().execute(commandBridge);

        // replace data from bridge
//        getData().replace(commandBridge.getData());

        // print text
        StringBuilder response = new StringBuilder();

        response.append(commandBridge.getText());

        // print products
        commandBridge.getNestedProducts()
                .stream().map(Product::toString)
                .forEach(response::append); todo*/

        // check internal stack
//        commandBridge.getInternalQueries().forEach(this::handle);

//        return new Message(response.toString());
        return new Message("todo");
    }

    private String getHelp() {
        return "Все доступные команды: \n" + commands.stream()
                .map(command -> String.format("%n" + helpPattern, command.getUsage(), command.getHelp()))
                .collect(Collectors.joining());
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    static class Message {
        private String message;

        public Message(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }

        public void setMessage(String message) {
            this.message = message.replaceAll("\n", "</br>");
        }
    }
}
