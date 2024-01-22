package com.serezka.lab5.chat.console_worker;

public abstract class ConsoleWorker {
    public abstract void send(String message);

    public void send(String pattern, Object... vals) {
        send(String.format(pattern, vals));
    }

    public abstract String get(String label);

    public void clear() {
        send("");
    }
}