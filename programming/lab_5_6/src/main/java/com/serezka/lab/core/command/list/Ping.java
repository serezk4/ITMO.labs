package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Ping extends Command {
    public Ping() {
        super("ping", "ping","test chat");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("pong");
    }
}
