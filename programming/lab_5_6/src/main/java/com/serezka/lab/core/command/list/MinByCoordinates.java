package com.serezka.lab.core.command.list;

import com.serezka.lab.core.database.model.Product;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinByCoordinates extends Command {
    public MinByCoordinates() {
        super("min_by_coordinates", "вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    @Override
    public void execute(Bridge bridge) {
        List<Product> userData = bridge.getData();

        if (userData.isEmpty()) {
            bridge.send("кажется, коллекция пустая. найти необходимый объект не получится.");
            return;
        }

        bridge.addNestedProduct(userData.stream().min(Product::compareTo).get());
    }
}
