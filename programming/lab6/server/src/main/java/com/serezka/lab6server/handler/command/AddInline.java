package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import com.serezka.lab6server.handler.io.format.FormatWorker;
import com.serezka.lab6server.handler.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddInline extends Command {
    FormatWorker formatWorker;

    public AddInline(FormatWorker formatWorker) {
        super("add .+", "добавить новый элемент в коллекцию (через консоль)");
        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Handler handler, Update update) {
        final String data = update.getMessage().split(" ", 2)[1]
                .replaceAll("\\+g", formatWorker.writeString(Collections.singletonList(new Product().generate())));

        List<Integer> existingIds = handler.getData().stream().map(Product::getId).toList();

        List<Product> formatted = formatWorker.readString(data)
                .stream().filter(product -> {
                    if (!existingIds.contains(product.getId())) return true;

                    handler.getChannel().send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно" +
                                    "\nпопробуйте еще раз с другими данными.", product.getId());
                    return false;

                }).toList();

        handler.getChannel().send("добавлено %d записей", formatted.size());
        handler.getData().addAll(formatted);
    }
}
