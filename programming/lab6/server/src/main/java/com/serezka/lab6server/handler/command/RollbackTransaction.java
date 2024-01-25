package com.serezka.lab6server.handler.command;

import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import com.serezka.lab6server.handler.transaction.TransactionManager;
import org.springframework.stereotype.Component;

@Component
public class RollbackTransaction extends Command{
    public RollbackTransaction() {
        super("(rollback transaction)|(rollback)", "закрыть транзакцию и не сохранять изменения");
    }

    @Override
    public void execute(Handler handler, Update update) {
        if (TransactionManager.isEmpty()) {
            handler.getChannel().send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        handler.getChannel().send("транзакция закрыта и изменения не приняты.");
        TransactionManager.close();
    }
}
