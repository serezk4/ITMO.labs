package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import org.springframework.stereotype.Component;

@Component
public class Clear extends Command {
    public Clear() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public void execute(Handler handler, Update update) {
        if (!handler.getChannel().get("вы уверены, что хотите удалить все записи из коллекции? [y/n]").matches("(y)|(Y)|(yes)|(да)|(д)|")) {
            handler.getChannel().send("хорошо, данные останутся в покое");
            return;
        }

        handler.getData().clear();
        handler.getChannel().send("данные успешно удалены");
    }
}
