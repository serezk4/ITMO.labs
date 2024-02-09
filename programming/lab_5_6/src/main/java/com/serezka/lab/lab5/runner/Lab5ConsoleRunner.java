package com.serezka.lab.lab5.runner;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.console.ConsoleWorker;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.runner.Runner;
import com.serezka.lab.lab5.hahdler.Chat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("lab5runner")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class Lab5ConsoleRunner implements Runner {
    Chat chat;
    ConsoleWorker consoleWorker;

    String inPattern;
    String outPattern;

    FormatWorker<Flat> formatWorker;

    public Lab5ConsoleRunner(@Qualifier("lab5") Chat chat,
                             @Qualifier("bufferedConsoleWorker") ConsoleWorker consoleWorker,
                             @Value("${chat.in.pattern}") String inPattern,
                             @Value("${chat.out.pattern}") String outPattern,
                             @Qualifier("csvFormatWorker") FormatWorker<Flat> formatWorker) {
        this.chat = chat;
        this.consoleWorker = consoleWorker;

        this.inPattern = inPattern;
        this.outPattern = outPattern;

        this.formatWorker = formatWorker;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {

                String[] data = consoleWorker.get(inPattern).split(" ");

                if (data.length == 0) {
                    consoleWorker.send("допущена ошибка в воде: слишком мало аргументов");
                    return;
                }

                Payload payload = Payload.builder()
                        .command(data[0])
                        .flats(Collections.emptySet())
                        .build();

                if (data.length >= 2) payload.setFlats(formatWorker.readString(data[Math.min(2, data.length - 1)]));
                if (data.length == 3) payload.setString(data[1]);

                Response response;
                try {
                    response = chat.handle(payload);
                } catch (Exception ex) {
                    response = Response.builder().message(ex.getMessage()).build();
                }

                if (response.getMessage() != null) consoleWorker.send(response.getMessage());
                if (response.getFlats() != null)
                    response.getFlats().stream().map(Flat::toString).forEach(consoleWorker::send);

                consoleWorker.skip();
            } catch (Exception ex) {
                consoleWorker.send(ex.getMessage());
            }
        }
    }
}
