package com.serezka.lab5.chat.command;

import com.serezka.lab5.chat.hahdler.Chat;
import com.serezka.lab5.chat.hahdler.Update;
import com.serezka.lab5.chat.transaction.Transaction;
import com.serezka.lab5.chat.transaction.TransactionManager;
import org.springframework.stereotype.Component;

@Component
public class BeginTransaction extends Command {
    public BeginTransaction() {
        super("(begin transaction)|(begin)", "открыть транзакцию");
    }

    @Override
    public void execute(Chat chat, Update update) {
        chat.getConsole().send("транзакция открыта");
        TransactionManager.add(new Transaction(chat.getUserData()));
    }
}
