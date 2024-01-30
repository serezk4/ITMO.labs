package com.serezka.lab.core.command.list;

import com.serezka.lab.core.transaction.Transaction;
import com.serezka.lab.core.transaction.TransactionManager;
import com.serezka.lab.core.user.Data;
import com.serezka.lab.core.command.Bridge;
import com.serezka.lab.core.command.Command;
import org.springframework.stereotype.Component;

@Component
public class BeginTransaction extends Command {
    public BeginTransaction() {
        super("(begin transaction)|(begin)", "открыть транзакцию");
    }

    @Override
    public void execute(Bridge bridge) {
        bridge.send("транзакция открыта");
        TransactionManager.add(new Transaction((Data) bridge.getData().clone()));
    }
}
