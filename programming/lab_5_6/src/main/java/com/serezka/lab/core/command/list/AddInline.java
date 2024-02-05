package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Flat;
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
    public AddInline() {
        super("add .+", "добавить новый элемент в коллекцию (через консоль)");
    }

    @Override
    public void execute(Bridge bridge) {
        List<Long> existingIds = bridge.getCurrentData().stream().map(Flat::getId).toList();

        Set<Flat> formatted = bridge.getInputData()
                .stream().filter(flat -> {
                    if (!existingIds.contains(flat.getId())) return true;

                    bridge.send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно" +
                                    "\nпопробуйте еще раз с другими данными.", flat.getId());
                    return false;

                }).collect(Collectors.toSet());

        bridge.send("добавлено %d записей", formatted.size());
        bridge.getCurrentData().addAll(formatted);
    }
}
