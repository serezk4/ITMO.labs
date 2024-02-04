package com.serezka.lab.core.configuration.io.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySource("classpath:application.properties")
public class RootFileConfiguration {
    @Bean("rootFile")
    public Path rootFile(@Value("${file.root}") String rootFilePath) throws IOException {
        Path path = Paths.get(rootFilePath);
        if (Files.notExists(path)) Files.createFile(path);
        return path;
    }
}
