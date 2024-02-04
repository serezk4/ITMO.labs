package com.serezka.lab.lab5.configuration.chat;

import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.lab5.hahdler.Chat;
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
                     @Value("${chat.help.pattern}") String helpPattern,
                     FlatService flatService) {
        return new Chat(commands, helpPattern, flatService);
    }
}
