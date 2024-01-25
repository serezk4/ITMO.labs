package com.serezka.lab6server.configuration.handler;

import com.serezka.lab6server.handler.command.Command;
import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.io.channel.ChannelWorker;
import com.serezka.lab6server.handler.io.format.FormatWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan("com.serezka.lab6server.handler.command")
public class HandlerConfiguration {
    @Bean
    public Handler handler(List<Command> commands,
                           @Qualifier("csvFormatWorker") FormatWorker formatWorker,
                           @Qualifier("TCPChannelWorker")ChannelWorker channelWorker) {
        Handler handler = new Handler(formatWorker, channelWorker);
        return handler;
    }
}
