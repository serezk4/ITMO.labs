package com.serezka.lab5.configuration.io.format;

import com.serezka.lab5.chat.io.format.FormatWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatWorkerConfiguration {
    @Bean
    public FormatWorker formatWorker(@Qualifier("csvFormatWorker") FormatWorker formatWorker) {
        return formatWorker;
    }
}
