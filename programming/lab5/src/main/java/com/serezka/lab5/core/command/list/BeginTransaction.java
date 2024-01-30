package com.serezka.lab5.core.command.list;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.transaction.Transaction;
import com.serezka.lab5.chat.transaction.TransactionManager;
import com.serezka.lab5.chat.user.Data;
import com.serezka.lab5.core.command.Bridge;
import com.serezka.lab5.core.command.Command;
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
