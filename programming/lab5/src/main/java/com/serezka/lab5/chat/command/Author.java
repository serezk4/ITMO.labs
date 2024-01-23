package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;

public class Author extends Command {
    public Author() {
        super("author", "кто натыкал этот код");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("""
                @Lab5 ~ Software engineering (1st course)
                2023-2024
                      
                      |\\      _,,,---,,_
                ZZZzz /,`.-'`'    -.  ;-;;,_
                     |,4-  ) )-,_. ,\\ (  `'-'
                    '---''(_/--'  `-'\\_)  Sergey Dorokhin ~ https://github.com/serezk4\s
                
                don't disturb the cat, he want to sleep
                """);
    }
}
