package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemoveGreater extends Command {
    FormatWorker<Flat> formatWorker;

    public RemoveGreater(FormatWorker<Flat> formatWorker) {
        super("remove_greater .+", "удалить из коллекции все элементы, превышающие заданный");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Bridge bridge) {
        final String data = bridge.getUpdate().getMessage().split(" ", 2)[1];

        Set<Flat> formatted = formatWorker.readString(data);

        if (formatted.isEmpty()) {
            bridge.send("ничего не было введено");
            return;
        }

        Flat max = Collections.max(formatted);
        if (formatted.size() > 1)
            bridge.send("так как было введено больше, чем одна запись, будет взята максимальная.");

        Set<Flat> toRemove = bridge.getData().stream().filter(temp -> temp.compareTo(max) > 0).collect(Collectors.toSet());
        bridge.getData().removeAll(toRemove);
        bridge.addNestedProducts(toRemove);
    }
}
