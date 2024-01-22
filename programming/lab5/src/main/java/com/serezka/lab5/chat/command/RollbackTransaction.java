package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.transaction.TransactionManager;

public class RollbackTransaction extends Command{
    public RollbackTransaction() {
        super("(rollback transaction)|(rollback)", "закрыть транзакцию и не сохранять изменения");
    }

    @Override
    public void execute(Chat chat, Update update) {
        if (TransactionManager.isEmpty()) {
            chat.getConsole().send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        chat.getConsole().send("транзакция закрыта и изменения не приняты.");
        TransactionManager.close();
    }
}
