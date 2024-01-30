package com.serezka.lab.lab6.server.configuration.controller.io.channel;

import com.serezka.lab.core.io.server.ServerWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelWorkerConfiguration {
    @Bean
    public ServerWorker channelWorker(@Qualifier("TCPServerWorker") ServerWorker serverWorker) {
        return serverWorker;
    }
}
