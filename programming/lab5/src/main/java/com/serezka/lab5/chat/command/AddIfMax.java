package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.io.format.FormatWorker;
import com.serezka.lab5.chat.object.Product;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AddIfMax extends Command{
    FormatWorker formatWorker;

    public AddIfMax(FormatWorker formatWorker) {
        super("add_if_max .+", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");

        this.formatWorker = formatWorker;
    }

    @Override
    public void execute(Chat chat, Update update) {
        String data = update.getMessage().split(" ", 2)[1];
        List<Product> formatted = formatWorker.readString(data);

        if (formatted.size() > 1) chat.getConsole().send(" ! будет взято максимальное значение из введенных данных ! ");

        Product inputMax = formatted.stream().max(Product::compareTo).orElseThrow();
        Optional<Product> currMax = chat.getUserData().stream().max(Product::compareTo);
    }
}
