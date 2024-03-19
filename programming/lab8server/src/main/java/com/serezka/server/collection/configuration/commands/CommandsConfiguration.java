package com.serezka.server.collection.configuration.commands;

import com.serezka.server.collection.execution.commands.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan("com.serezka.server.collection.execution.commands")
public class CommandsConfiguration {
    @Bean("commands")
    public List<Command> commands(List<Command> commands) {
        return commands;
    }
}
