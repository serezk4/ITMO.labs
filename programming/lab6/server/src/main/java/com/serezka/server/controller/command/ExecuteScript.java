/**
 * THIS COMMAND CAN't RUN WITH CLIENT-SERVER ARCHITECTURE
 */


//package com.serezka.lab6server.handler.command;
//
//import com.serezka.lab6server.handler.handler.Handler;
//import com.serezka.lab6server.handler.handler.Update;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//@Component
//@Log4j2
//public class ExecuteScript extends Command {
//    public ExecuteScript() {
//        super("execute_script .+\\.txt", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
//    }
//
//    @Override
//    public void execute(Handler handler, Update update) {
//        final String filePath = update.getMessage().split(" ", 2)[1];
//
//        StringBuilder data = new StringBuilder();
//        try (BufferedReader fileReader = Files.newBufferedReader(Path.of(filePath))) {
//            while (fileReader.ready()) data.append(fileReader.readLine()).append("\n");
//        } catch (IOException e) {
//            log.warn(e.getMessage());
//        }
//
//        handler.getChannel().send("производится выполнение операций...");
//
//        int counter = 0;
//        for (String command : data.toString().split("\n")) {
//            handler.getChannel().send("Операция #%d [%s]", ++counter, command);
//            handler.execute(command);
//        }
//
//        handler.getChannel().send("все операции выполнены успешно.");
//    }
//}
