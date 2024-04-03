package com.serezka.localization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalizationConfiguration {
    @Bean
    public Localization localization() {
        return Localization.getInstance();
    }
}
