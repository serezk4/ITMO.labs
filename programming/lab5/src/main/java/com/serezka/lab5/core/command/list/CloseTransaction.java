package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.transaction.TransactionManager;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class CloseTransaction extends Command {
    public CloseTransaction() {
        super("(close transaction)|(close)", "закрыть транзакцию и применить изменения");
    }

    @Override
    public void execute(Bridge bridge) {
        if (TransactionManager.isEmpty()) {
            bridge.send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        bridge.send("транзакция закрыта и изменения применены");
        bridge.setData(TransactionManager.close().getData());
    }
}
