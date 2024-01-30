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
public class RemoveGreater extends Command {
    FormatWorker formatWorker;

    public RemoveGreater(FormatWorker formatWorker) {
        super("remove_greater .+", "удалить из коллекции все элементы, превышающие заданный");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Chat chat, Update update) {
        final String data = update.getMessage().split(" ", 2)[1];

        List<Product> formatted = formatWorker.readString(data);

        if (formatted.isEmpty()) {
            chat.getConsole().send("ничего не было введено");
            return;
        }

        Product max = Collections.max(formatted);
        if (formatted.size() > 1)
            chat.getConsole().send("так как было введено больше, чем одна запись, будет взята максимальная.");

        chat.getData().removeGreaterThan(max)
                .stream().map(Product::toString)
                .forEach(chat.getConsole()::send);
    }
}
