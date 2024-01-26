package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.io.format.FormatWorker;
import com.serezka.server.controller.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

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
    public void execute(Handler handler, Payload payload) {
        if (payload.getProduct() == null) {
            handler.getChannel().send("Ошибка! Нету данных о продукте!");
            return;
        }

        Product inputMax = payload.getProduct();
        Optional<Product> currMax = handler.getData().stream().max(Product::compareTo);

        if (handler.getData().stream().map(Product::getId).anyMatch(inputMax.getId()::equals)) {
            handler.getChannel().send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно\nпопробуйте еще раз с другими данными.");
            return;
        }

        if (currMax.isEmpty()) {
            handler.getChannel().send("кажется, коллекция пуста. элемент будет добавлен.");
            handler.getData().add(inputMax);
            return;
        }

        if (currMax.get().compareTo(inputMax) < 0) {
            handler.getChannel().send("введенный элемент попал в коллекцию");
            handler.getData().add(inputMax);
            return;
        }

        handler.getChannel().send("введенный элемент не попал в коллекцию, ведь его значение не превышает наибольший элемент");
    }
}
