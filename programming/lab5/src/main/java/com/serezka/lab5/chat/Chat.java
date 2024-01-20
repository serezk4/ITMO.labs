package com.serezka.lab5.chat;

import com.serezka.lab5.chat.console_worker.ConsoleWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:chat.properties")
public class Chat implements Runnable {
    String name;
    String outPattern;
    String inPattern;

    ConsoleWorker console;

    public Chat(@Value("${chat.name}") String name,
                @Value("${chat.out.pattern}") String outPattern, @Value("${chat.in.pattern}") String inPattern,
                ConsoleWorker console) {
        this.name = name;
        this.outPattern = outPattern;
        this.inPattern = inPattern;

        this.console = console;
    }


    @Override
    public void run() {
        console.send("Chat \"%s\"", name);

        for (;;) {
            console.send("\033[H\033[2J");

            final String input = console.get(inPattern);



            console.send("введена некорректная команда, help - все команды");
        }
    }
}
