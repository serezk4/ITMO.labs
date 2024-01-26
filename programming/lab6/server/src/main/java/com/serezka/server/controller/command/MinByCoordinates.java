package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.object.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinByCoordinates extends Command{
    public MinByCoordinates() {
        super("min_by_coordinates", "вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        List<Product> userData = handler.getData();

        if (userData.isEmpty()) {
            handler.getChannel().send("кажется, коллекция пустая. найти необходимый объект не получится.");
            return;
        }

        Product minimalByCoordinates = userData.stream()
                .min(Product::compareTo).get();

        handler.getChannel().send(minimalByCoordinates);
    }
}
