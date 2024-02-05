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
    public RemoveGreater(FormatWorker<Flat> formatWorker) {
        super("remove_greater", "remove_greater", "удалить из коллекции все элементы, превышающие заданный");
    }

    @Override
    public void execute(Bridge bridge) {
//        final String data = bridge.getUpdate().getMessage().split(" ", 2)[1];

        Set<Flat> input = bridge.getInputData();

        if (input.isEmpty()) {
            bridge.send("ничего не было введено");
            return;
        }

        Flat max = Collections.max(input);
        if (input.size() > 1)
            bridge.send("так как было введено больше, чем одна запись, будет взята максимальная.");

        Set<Flat> toRemove = bridge.getCurrentData().stream().filter(temp -> temp.compareTo(max) > 0).collect(Collectors.toSet());
        bridge.getCurrentData().removeAll(toRemove);
        bridge.addNestedProducts(toRemove);
    }
}
