package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.io.format.FormatWorker;
import com.serezka.server.controller.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemoveGreater extends Command{
    FormatWorker formatWorker;

    public RemoveGreater(FormatWorker formatWorker) {
        super("remove_greater .+", "удалить из коллекции все элементы, превышающие заданный");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        if (payload.getProduct() == null) {
            handler.getChannel().send("Ошибка! Нету данных о продукте!");
            return;
        }

        Product max = payload.getProduct();
        handler.getChannel().send(handler.getData()
                .removeGreaterThan(max)
                .stream().toList());
    }
}
