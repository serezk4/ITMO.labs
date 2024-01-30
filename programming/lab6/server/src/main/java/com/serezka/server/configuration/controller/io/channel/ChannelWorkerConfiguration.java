package com.serezka.server.configuration.controller.io.channel;

import com.serezka.server.controller.io.server.ChannelWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelWorkerConfiguration {
    @Bean
    public ChannelWorker channelWorker(@Qualifier("TCPChannelWorker") ChannelWorker channelWorker) {
        return channelWorker;
    }
}
