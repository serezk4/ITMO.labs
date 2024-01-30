package com.serezka.client.configuration.chat.handler;

import com.serezka.client.chat.handler.Handler;
import com.serezka.client.chat.io.client.ChannelWorker;
import com.serezka.client.chat.io.console.ConsoleWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfiguration {
    @Bean
    public Handler handler(@Qualifier("TCPChannelWorker")ChannelWorker channelWorker,
                           @Qualifier("bufferedConsoleWorker")ConsoleWorker consoleWorker) {
        return new Handler(channelWorker, consoleWorker);
    }
}
