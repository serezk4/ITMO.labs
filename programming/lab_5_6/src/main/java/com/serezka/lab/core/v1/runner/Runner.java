package com.serezka.lab.core.v1.runner;

public interface Runner extends Runnable {
    default void run() {};
}
