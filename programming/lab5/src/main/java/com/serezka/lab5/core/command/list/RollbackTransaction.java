package com.serezka.lab5.core.command.list;

import com.serezka.lab5.core.transaction.TransactionManager;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class RollbackTransaction extends Command {
    public RollbackTransaction() {
        super("(rollback transaction)|(rollback)", "закрыть транзакцию и не сохранять изменения");
    }

    @Override
    public void execute(Bridge bridge) {
        if (TransactionManager.isEmpty()) {
            bridge.send("ошибка! ни одной транзакции не открыто.");
            return;
        }

        bridge.send("транзакция закрыта и изменения не приняты.");
        TransactionManager.close();
    }
}
