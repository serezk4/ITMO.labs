package com.serezka.lab5.core.command.list;

import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Ping extends Command {
    public Ping() {
        super("ping", "test chat");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("pong");
    }
}
