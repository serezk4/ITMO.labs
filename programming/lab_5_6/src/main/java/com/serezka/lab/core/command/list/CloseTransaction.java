package com.serezka.lab.core.command.list;

import com.serezka.lab.core.transaction.TransactionManager;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
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
        bridge.getData().replace(TransactionManager.close().getData());
    }
}
