package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.transaction.Transaction;
import com.serezka.lab5.chat.transaction.TransactionManager;

public class BeginTransaction extends Command {
    public BeginTransaction() {
        super("(begin transaction)|(begin)", "открыть транзакцию");
    }

    @Override
    public void execute(Chat chat, Update update) {
        // todo
        TransactionManager.add(new Transaction());
    }
}
