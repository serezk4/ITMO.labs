package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddInline extends Command {
    public AddInline() {
        super("add .+", "добавить новый элемент в коллекцию (через консоль)");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        if (payload.getProduct() == null) {
            handler.getChannel().send("Ошибка! Нету данных о продукте!");
            return;
        }

        List<Integer> existingIds = handler.getData().stream().map(Product::getId).toList();

        if (existingIds.contains(payload.getProduct().getId())) {
            handler.getChannel().sendf("в коллекции уже находится элемент с %d id, добавить новый будет невозможно" +
                    "\nпопробуйте еще раз с другими данными.", payload.getProduct().getId());

            return;
        }

        handler.getChannel().send("запись успешно добавлена");
        handler.getData().add(payload.getProduct());
    }
}
