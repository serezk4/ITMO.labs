package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.transaction.TransactionManager;
import org.springframework.stereotype.Component;

@Component
public class CloseTransaction extends Command{
    public CloseTransaction() {
        super("(close transaction)|(close)", "закрыть транзакцию и применить изменения");
    }

    @Override
    public void execute(Chat chat, Update update) {
        if (TransactionManager.isEmpty()) {
            chat.getConsole().send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        chat.getConsole().send("транзакция закрыта и изменения применены");
        chat.setUserData(TransactionManager.close().getUserData());
    }
}
