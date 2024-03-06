package com.serezka.lab.core.v1.utils;

import com.serezka.lab.core.v1.command.Command;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/core")
@RequiredArgsConstructor
@ComponentScan("com.serezka.lab.core.command.list")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CoreController {
    private final List<Command> commands;

    @GetMapping("/getcommands")
    public ResponseEntity<List<Command>> getCommands() {
        return ResponseEntity.ok(commands);
    }

    record CommandInfo(String usage, String help) {}
}
