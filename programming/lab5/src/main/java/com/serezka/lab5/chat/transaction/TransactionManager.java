package com.serezka.lab5.chat.transaction;

import com.serezka.lab5.chat.transaction.exceptions.TransactionsStackEmptyException;

import java.util.Stack;

public class TransactionManager {
    private static final Stack<Transaction> transactions = new Stack<>();

    public static void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public static Transaction get() {
        if (isEmpty()) throw new TransactionsStackEmptyException();
        return transactions.peek();
    }

    public static Transaction close() {
        if (isEmpty()) throw new TransactionsStackEmptyException();
        return transactions.pop();
    }

    public static boolean isEmpty() {
        return transactions.empty();
    }
}
