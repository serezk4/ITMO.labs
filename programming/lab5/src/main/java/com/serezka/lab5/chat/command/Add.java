package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.io.format.FormatWorker;
import com.serezka.lab5.chat.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Add extends Command{
    FormatWorker formatWorker;

    public Add(FormatWorker formatWorker) {
        super("add .+", "добавить новый элемент в коллекцию");
        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Chat chat, Update update) {
        String data = update.getMessage().split(" ", 2)[1];
        List<Product> formatted = formatWorker.readString(data);

        chat.getConsole().send("добавлено %d записей", formatted.size());
        chat.getUserData().addAll(formatted);
    }
}
