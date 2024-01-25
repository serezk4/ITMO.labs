package com.serezka.lab6server.handler.command;


import com.serezka.lab6server.handler.handler.Handler;
import com.serezka.lab6server.handler.handler.Update;
import com.serezka.lab6server.handler.transaction.TransactionManager;
import org.springframework.stereotype.Component;

@Component
public class CloseTransaction extends Command{
    public CloseTransaction() {
        super("(close transaction)|(close)", "закрыть транзакцию и применить изменения");
    }

    @Override
    public void execute(Handler handler, Update update) {
        if (TransactionManager.isEmpty()) {
            handler.getChannel().send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        handler.getChannel().send("транзакция закрыта и изменения применены");
        handler.setData(TransactionManager.close().getData());
    }
}
