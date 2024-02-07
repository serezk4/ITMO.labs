package com.serezka.lab.core.runner;

public interface Runner extends Runnable {
    default void run() {};
}
