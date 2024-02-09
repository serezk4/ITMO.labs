package com.serezka.lab.core.configuration.command;

import com.serezka.lab.core.command.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan("com.serezka.lab.core.command")
public class CommandsConfiguration {
    @Bean("commands")
    public List<Command> commands(List<Command> commands) {
        return commands;
    }
}
