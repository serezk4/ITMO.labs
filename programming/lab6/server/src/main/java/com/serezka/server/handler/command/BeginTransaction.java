package com.serezka.server.handler.command;


import com.serezka.server.handler.handler.Handler;
import com.serezka.server.handler.handler.Update;
import com.serezka.server.handler.transaction.Transaction;
import com.serezka.server.handler.transaction.TransactionManager;
import com.serezka.server.handler.user.Data;
import org.springframework.stereotype.Component;

@Component
public class BeginTransaction extends Command {
    public BeginTransaction() {
        super("(begin transaction)|(begin)", "открыть транзакцию");
    }

    @Override
    public void execute(Handler handler, Update update) {
        handler.getChannel().send("транзакция открыта");
        TransactionManager.add(new Transaction((Data) handler.getData().clone()));
    }
}
