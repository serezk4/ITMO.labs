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
public class RemoveGreater extends Command {
    FormatWorker formatWorker;

    public RemoveGreater(FormatWorker formatWorker) {
        super("remove_greater .+", "удалить из коллекции все элементы, превышающие заданный");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Bridge bridge) {
        final String data = bridge.getUpdate().getMessage().split(" ", 2)[1];

        List<Product> formatted = formatWorker.readString(data);

        if (formatted.isEmpty()) {
            bridge.send("ничего не было введено");
            return;
        }

        Product max = Collections.max(formatted);
        if (formatted.size() > 1)
            bridge.send("так как было введено больше, чем одна запись, будет взята максимальная.");

        bridge.addNestedProducts(bridge.getData().removeGreaterThan(max).stream().toList());
    }
}
