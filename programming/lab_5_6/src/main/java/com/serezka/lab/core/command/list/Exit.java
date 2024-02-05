package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class Exit extends Command {
    public Exit() {
        super("exit", "exit","завершить программу");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("бб");
        System.exit(228);
    }
}
