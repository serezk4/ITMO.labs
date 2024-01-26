package com.serezka.server.configuration.controller.handler;

import com.serezka.server.controller.command.Command;
import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.io.channel.ChannelWorker;
import com.serezka.server.controller.io.format.FormatWorker;
import com.serezka.server.controller.seralizator.JsonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HandlerConfiguration {
    @Bean
    public Handler handler(List<Command> commands,
                           ChannelWorker channelWorker,
                           @Qualifier("csvFormatWorker") FormatWorker formatWorker,
                           JsonSerializer jsonSerializer) {
        return new Handler(formatWorker, channelWorker, commands);
    }
}
