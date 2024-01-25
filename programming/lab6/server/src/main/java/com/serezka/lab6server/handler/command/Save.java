package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import com.serezka.lab6server.handler.io.format.FormatWorker;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PropertySource("classpath:files.properties")
@Log4j2
public class Save extends Command {
    FormatWorker formatWorker;

    SimpleDateFormat dateFormat;
    String nameFormat;
    String saveDir;

    public Save(FormatWorker formatWorker,
                @Value("${file.date.format}") String dateFormat,
                @Value("${file.name.format}") String nameFormat,
                @Value("${file.save.dir}") String saveDir) {
        super("save", "сохранить коллекцию в файл");

        this.formatWorker = formatWorker;

        this.dateFormat = new SimpleDateFormat(dateFormat);
        this.nameFormat = nameFormat;
        this.saveDir = saveDir;
    }

    @Override
    public void execute(Handler chat, Update update) {
        try {
            final Path path = Paths.get(saveDir +
                    String.format(nameFormat, dateFormat.format(new Date()), (int) (Math.random() * 1000)));
            if (Files.notExists(path)) Files.createFile(path);

            formatWorker.write(chat.getData(), path);

            chat.getChannel().send("Данные успешно сохранены в файл %s", path.toString());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }
}
