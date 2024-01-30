package com.serezka.lab5.core.transaction;

import java.util.EmptyStackException;
import java.util.Stack;

public class TransactionManager {
    private static final Stack<Transaction> transactions = new Stack<>();

    public static void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public static Transaction get() {
        if (isEmpty()) throw new EmptyStackException();
        return transactions.peek();
    }

    public static Transaction close() {
        if (isEmpty()) throw new EmptyStackException();
        return transactions.pop();
    }

    public static boolean isEmpty() {
        return transactions.empty();
    }

    public static int depth() {
        return transactions.size();
    }
}
