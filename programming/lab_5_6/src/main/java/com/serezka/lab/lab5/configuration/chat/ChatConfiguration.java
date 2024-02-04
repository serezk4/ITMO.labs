package com.serezka.lab.lab5.configuration.chat;

import com.serezka.lab.lab5.hahdler.Chat;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.io.console.ConsoleWorker;
import com.serezka.lab.core.io.format.FormatWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.serezka.lab.core.command")
@PropertySource("classpath:chat.properties")
public class ChatConfiguration {
    @Bean("lab5")
    public Chat chat(List<Command> commands,
                     @Value("${chat.name}") String name,
                     @Value("${chat.out.pattern}") String outPattern,
                     @Value("${chat.in.pattern}") String inPattern,
                     @Value("${chat.help.pattern}") String helpPattern,
                     @Qualifier("bufferedConsoleWorker") ConsoleWorker console,
                     @Qualifier("csvFormatWorker") FormatWorker formatWorker) {
        Chat chat = new Chat(name, outPattern, inPattern, helpPattern, console, formatWorker);
        chat.setCommands(commands);
        return chat;
    }
}
