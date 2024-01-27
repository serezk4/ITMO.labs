package com.serezka.server.controller.command;

import com.serezka.server.controller.handler.Handler;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.transaction.Transaction;
import com.serezka.server.controller.transaction.TransactionManager;
import com.serezka.server.controller.user.Data;
import org.springframework.stereotype.Component;

@Component
public class BeginTransaction extends Command {
    public BeginTransaction() {
        super("(begin transaction)|(begin)", "открыть транзакцию");
    }

    @Override
    public void execute(Handler handler, Payload payload) {
        handler.getChannel().send("транзакция открыта");
        TransactionManager.add(new Transaction((Data) handler.getData().clone()));
    }
}
