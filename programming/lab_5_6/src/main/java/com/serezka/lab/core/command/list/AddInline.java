package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddInline extends Command {
    FlatService flatService;

    public AddInline(FlatService flatService) {
        super("add", "add", "добавить новый элемент(ы) в коллекцию");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());
        Set<Long> existingIds = collection.stream().map(Flat::getId).collect(Collectors.toSet());

        bridge.getInputData()
                .stream().filter(flat -> {
                    if (!existingIds.contains(flat.getId())) {
                        bridge.send("[%s]: элемент успешно добавлен", flat.getName());
                        return true;
                    }

                    bridge.send("[%s]: в коллекции уже находится элемент с %d id", flat.getName(), flat.getId());
                    return false;
                }).forEach(flatService::save);
    }
}
