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
public class RemoveGreater extends Command{
    FormatWorker formatWorker;

    public RemoveGreater(FormatWorker formatWorker) {
        super("remove_greater .+", "удалить из коллекции все элементы, превышающие заданный");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Handler handler, Update update) {
        final String data = update.getMessage().split(" ", 2)[1];

        List<Product> formatted = formatWorker.readString(data);

        if (formatted.isEmpty()) {
            handler.getChannel().send("ничего не было введено");
            return;
        }

        Product max = Collections.max(formatted);
        if (formatted.size() > 1)
            handler.getChannel().send("так как было введено больше, чем одна запись, будет взята максимальная.");

        handler.getData().removeGreaterThan(max)
                .stream().map(Product::toString)
                .forEach(handler.getChannel()::send);
    }
}
