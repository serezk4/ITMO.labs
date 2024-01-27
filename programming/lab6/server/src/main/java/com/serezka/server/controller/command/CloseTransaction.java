package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.transaction.TransactionManager;
import org.springframework.stereotype.Component;

@Component
public class CloseTransaction extends Command{
    public CloseTransaction() {
        super("(close transaction)|(close)", "закрыть транзакцию и применить изменения");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        if (TransactionManager.isEmpty()) {
            handler.getChannel().send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        handler.getChannel().send("транзакция закрыта и изменения применены");
        handler.setData(TransactionManager.close().getData());
    }
}
