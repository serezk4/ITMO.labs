package com.serezka.lab5.core.io.console;

import com.serezka.lab5.chat.transaction.TransactionManager;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Log4j2
public class BufferedConsoleWorker extends ConsoleWorker {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    @Override
    public void send(String message) {
        try {
            writer.append(message).append("\n").flush();
        } catch (IOException ioException) {
            log.warn(ioException.getMessage());
        }
    }

    @Override
    public String get(String label) {
        try {
            final Date currentDate = new Date();
            writer.append(String.format(label, "|".repeat(TransactionManager.depth()), currentDate, currentDate, currentDate)).append(": ").flush();
            return reader.readLine();
        } catch (IOException ioException) {
            log.warn(ioException.getMessage());
            return null;
        }
    }
}
