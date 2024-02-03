package com.serezka.lab.lab6.server.configuration.controller.handler;

import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.lab6.server.handler.Server;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServerConfiguration {
    @Bean
    public Server server(List<Command> commands,
                         @Qualifier("csvFormatWorker") FormatWorker formatWorker) {
        return new Server(formatWorker, commands);
    }
}
