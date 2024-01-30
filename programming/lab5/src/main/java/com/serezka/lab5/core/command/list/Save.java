package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.io.format.FormatWorker;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
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
    public void execute(Bridge bridge) {
        try {
            final Path path = Paths.get(saveDir +
                    String.format(nameFormat, dateFormat.format(new Date()), (int) (Math.random() * 1000)));
            if (Files.notExists(path)) Files.createFile(path);

            formatWorker.write(bridge.getData(), path);

            bridge.send("Данные успешно сохранены в файл %s", path.toString());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            bridge.send(ex.getMessage());
        }
    }
}
