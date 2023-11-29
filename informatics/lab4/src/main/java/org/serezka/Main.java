package org.serezka;

import org.serezka.parser.formats.YAML;

import java.util.List;

/**
 * @variant: 34
 * @task YAML -> XML, Суббота
 *
 * @author serezk4
 * @subject informatics
 * @lab 4
 * @date 29.11.2023
 */

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        new YAML().add(List.of("123"));
    }
}