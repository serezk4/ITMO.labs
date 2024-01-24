package com.serezka.lab5;

import com.serezka.lab5.chat.hahdler.Chat;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@ToString
public class Lab5 implements ApplicationRunner {
    Chat chat;

    public static void main(String[] args) {
        SpringApplication.run(Lab5.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(chat).start();
    }
}
