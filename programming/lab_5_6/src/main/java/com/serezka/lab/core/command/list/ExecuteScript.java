package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Component
@Log4j2
public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script .+\\.txt", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    @Override
    public void execute(Bridge bridge) {
        final String filePath = bridge.getInputText();

        StringBuilder data = new StringBuilder();
        try (BufferedReader fileReader = Files.newBufferedReader(Path.of(filePath))) {
            while (fileReader.ready()) data.append(fileReader.readLine()).append("\n");
        } catch (IOException e) {
            log.warn(e.getMessage());
            bridge.send(e.getMessage());
        }

        bridge.addInternalQueries(Arrays.stream(data.toString().split("\n")).toList());
    }
}
