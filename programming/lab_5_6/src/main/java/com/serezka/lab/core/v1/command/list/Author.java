package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Author extends Command {
    public Author() {
        super("author","author", "кто натыкал этот код");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("""
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
