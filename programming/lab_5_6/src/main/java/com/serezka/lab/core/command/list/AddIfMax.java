package com.serezka.lab.core.command.list;

import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import com.serezka.lab.core.database.model.Flat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddIfMax extends Command {
    public AddIfMax() {
        super("add_if_max .+", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
    }

    @Override
    public void execute(Bridge bridge) {
        if (bridge.getInputData().size() > 1)
            bridge.send("будет добавлен максимальный элемент из введенных данных");

        Flat inputMax = bridge.getInputData().stream().max(Flat::compareTo).orElseThrow();
        Optional<Flat> currMax = bridge.getCurrentData().stream().max(Flat::compareTo);

        if (bridge.getCurrentData().stream().map(Flat::getId).anyMatch(inputMax.getId()::equals)) {
            bridge.send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно\nпопробуйте еще раз с другими данными.");
            return;
        }

        if (currMax.isEmpty()) {
            bridge.send("кажется, коллекция пуста. элемент будет добавлен.");
            bridge.getCurrentData().add(inputMax);
            return;
        }

        if (currMax.get().compareTo(inputMax) < 0) {
            bridge.send("введенный элемент попал в коллекцию");
            bridge.getCurrentData().add(inputMax);
            return;
        }

        bridge.send("введенный элемент не попал в коллекцию, ведь его значение не превышает наибольший элемент");
    }
}
