package com.serezka.lab.core.v1.io.console;

public abstract class ConsoleWorker {
    public abstract void send(String message);

    public void send(String pattern, Object... vals) {
        send(String.format(pattern, vals));
    }

    public abstract String get(String label);

    public void clear() {
        send("\n".repeat(30));
    }

    public void skip() {
        send("");
    }
}
