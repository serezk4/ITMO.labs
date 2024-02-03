package com.serezka.lab.core.command.list;

import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.database.model.Product;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddIfMax extends Command {
    FormatWorker formatWorker;

    public AddIfMax(FormatWorker formatWorker) {
        super("add_if_max .+", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Bridge bridge) {
        final String data = bridge.getUpdate().getMessage().split(" ", 2)[1];
        List<Product> formatted = formatWorker.readString(data);

        if (formatted.size() > 1)
            bridge.send("будет добавлен максимальный элемент из введенных данных");

        Product inputMax = formatted.stream().max(Product::compareTo).orElseThrow();
        Optional<Product> currMax = bridge.getData().stream().max(Product::compareTo);

        if (bridge.getData().stream().map(Product::getId).anyMatch(inputMax.getId()::equals)) {
            bridge.send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно\nпопробуйте еще раз с другими данными.");
            return;
        }

        if (currMax.isEmpty()) {
            bridge.send("кажется, коллекция пуста. элемент будет добавлен.");
            bridge.getData().add(inputMax);
            return;
        }

        if (currMax.get().compareTo(inputMax) < 0) {
            bridge.send("введенный элемент попал в коллекцию");
            bridge.getData().add(inputMax);
            return;
        }

        bridge.send("введенный элемент не попал в коллекцию, ведь его значение не превышает наибольший элемент");
    }
}