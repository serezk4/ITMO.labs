package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.service.FlatService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddIfMax extends Command {
    FlatService flatService;

    public AddIfMax(FlatService flatService) {
        super("add_if_max", "add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");

        this.flatService = flatService;
    }

    @Override
    public void execute(Bridge bridge) {
        if (bridge.getInputData().isEmpty()) {
            bridge.send("вы ничего не ввели");
            return;
        }

        Set<Flat> collection = flatService.findAllByUserId(bridge.getUserId());
        Set<Long> existingIds = collection.stream().map(Flat::getId).collect(Collectors.toSet());

        Optional<Flat> max = collection.stream().max(Flat::compareTo);

        if (collection.stream().map(Flat::getId).anyMatch(bridge.getInputData().stream().map(Flat::getId).collect(Collectors.toSet())::contains)) {
            bridge.send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно\nпопробуйте еще раз с другими данными.");
            return;
        }

        bridge.getInputData()
                .stream().filter(flat -> {
                    if (existingIds.contains(flat.getId())) {
                        bridge.send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно\nпопробуйте еще раз с другими данными.", flat.getId());
                        return false;
                    }

                    if (max.isEmpty() || flat.compareTo(max.get()) < 0) {
                        bridge.send("Flat#%s ниже максимального элемента");
                        return false;
                    }

                    bridge.addNestedFlat(flat);
                    return true;
                }).forEach(flatService::save);

        bridge.send("Добавленные значения:");
    }
}
