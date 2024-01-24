package com.serezka.lab5.configuration.chat;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.io.console.ConsoleWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.serezka.lab5.chat.command")
@PropertySource("classpath:chat.properties")
@RequiredArgsConstructor
public class ChatConfiguration {
    @Bean
    public Chat chat(List<Command> commands,
                     @Value("${chat.name}") String name,
                     @Value("${chat.out.pattern}") String outPattern,
                     @Value("${chat.in.pattern}") String inPattern,
                     @Value("${chat.help.pattern}") String helpPattern,
                     @Qualifier("bufferedConsoleWorker") ConsoleWorker console) {
        Chat chat = new Chat(name, outPattern, inPattern, helpPattern, console);
        chat.setCommands(commands);
        return chat;
    }
}
