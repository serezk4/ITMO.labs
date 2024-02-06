package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
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
    FlatService flatService;

    public RemoveGreater(FlatService flatService) {
        super("remove_greater", "remove_greater", "удалить из коллекции все элементы, превышающие заданный");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());
        Set<Flat> input = bridge.getInputData();

        if (input.isEmpty()) {
            bridge.send("ничего не было введено");
            return;
        }

        Flat max = Collections.max(input);
        if (input.size() > 1)
            bridge.send("так как было введено больше, чем одна запись, будет взята максимальная.");

        Set<Flat> toRemove = collection.stream().filter(temp -> temp.compareTo(max) > 0).collect(Collectors.toSet());
        toRemove.forEach(flat -> flatService.removeByIdAndUserId(flat.getId(), bridge.getUserId()));
        bridge.addNestedProducts(toRemove);
    }
}
