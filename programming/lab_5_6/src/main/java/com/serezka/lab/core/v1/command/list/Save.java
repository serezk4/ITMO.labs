package com.serezka.lab.core.v1.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.v1.io.format.FormatWorker;
import com.serezka.lab.core.v1.command.Bridge;
import com.serezka.lab.core.v1.command.Command;
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
    FormatWorker<Flat> formatWorker;

    SimpleDateFormat dateFormat;
    String nameFormat;
    String saveDir;

    FlatService flatService;

    public Save(FormatWorker<Flat> formatWorker,
                @Value("${file.date.format}") String dateFormat,
                @Value("${file.name.format}") String nameFormat,
                @Value("${file.save.dir}") String saveDir, FlatService flatService) {
        super("save", "save", "сохранить коллекцию в файл");

        this.formatWorker = formatWorker;

        this.dateFormat = new SimpleDateFormat(dateFormat);
        this.nameFormat = nameFormat;
        this.saveDir = saveDir;
        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        try {
            final Path path = Paths.get(saveDir +
                    String.format(nameFormat, dateFormat.format(new Date()), (int) (Math.random() * 1000)));
            if (Files.notExists(path)) Files.createFile(path);

            formatWorker.write(flatService.findAllByUserId(bridge.getUserId()), path);

            bridge.send("Данные успешно сохранены в файл %s", path.toString());
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            bridge.send(ex.getMessage());
        }
    }
}
