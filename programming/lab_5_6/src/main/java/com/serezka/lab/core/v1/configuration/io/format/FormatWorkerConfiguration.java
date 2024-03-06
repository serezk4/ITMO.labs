package com.serezka.lab.core.v1.configuration.io.format;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.v1.io.format.FormatWorker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatWorkerConfiguration {
    @Bean
    public FormatWorker<Flat> formatWorker(@Qualifier("csvFormatWorker") FormatWorker<Flat> formatWorker) {
        return formatWorker;
    }
}
