package com.serezka.lab;

import com.serezka.lab.core.io.console.ConsoleWorker;
import com.serezka.lab.lab5.hahdler.Chat;
import com.serezka.lab.lab6.server.handler.Server;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ToString
@Log4j2
public class Application implements ApplicationRunner {
    Chat chat;
    Server server;

    ConsoleWorker consoleWorker;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        final String mode = consoleWorker.get("select mode [chat/server]: ");

        if (mode.equalsIgnoreCase("server")) {
            new Thread(server).start();
            return;
        }

        if (mode.equalsIgnoreCase("chat")) {
            new Thread(chat).start();
            return;
        }

        log.warn("can't start selected mode!");
    }
}
