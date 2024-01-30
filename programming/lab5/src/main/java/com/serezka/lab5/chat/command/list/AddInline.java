package com.serezka.lab5.chat.command.list;

import com.serezka.lab5.chat.command.Command;
import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.io.format.FormatWorker;
import com.serezka.lab5.chat.object.Product;
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
    public void execute(Chat chat, Update update) {
        final String data = update.getMessage().split(" ", 2)[1]
                .replaceAll("\\+g", formatWorker.writeString(Collections.singletonList(new Product().generate())));

        List<Integer> existingIds = chat.getData().stream().map(Product::getId).toList();

        List<Product> formatted = formatWorker.readString(data)
                .stream().filter(product -> {
                    if (!existingIds.contains(product.getId())) return true;

                    chat.getConsole().send("в коллекции уже находится элемент с %d id, добавить новый будет невозможно" +
                                    "\nпопробуйте еще раз с другими данными.", product.getId());
                    return false;

                }).toList();

        chat.getConsole().send("добавлено %d записей", formatted.size());
        chat.getData().addAll(formatted);
    }
}
