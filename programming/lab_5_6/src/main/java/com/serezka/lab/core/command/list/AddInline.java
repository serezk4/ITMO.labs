package com.serezka.lab.core.command.list;

import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.database.model.Product;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
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
    public void execute(Bridge bridge) {
        final String data = bridge.getUpdate().getMessage().split(" ", 2)[1]
                .replaceAll("\\+g", formatWorker.writeString(Collections.singletonList(new Product().generate())));

        List<Integer> existingIds = bridge.getData().stream().map(Product::getId).toList();

        List<Product> formatted = formatWorker.readString(data)
                .stream().filter(product -> {
                    if (!existingIds.contains(product.getId())) return true;

                    bridge.send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно" +
                                    "\nпопробуйте еще раз с другими данными.", product.getId());
                    return false;

                }).toList();

        bridge.send("добавлено %d записей", formatted.size());
        bridge.getData().addAll(formatted);
    }
}
