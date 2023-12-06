package org.serezka;

import org.serezka.parser.formats.YAML;
import org.serezka.parser.parser.YAMLtoXMLConverter;

import java.io.BufferedReader;
import java.io.FileReader;

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
    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    private void run() throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\serezk4\\IdeaProjects\\ITMO.labs\\programming\\ITMO.labs\\informatics\\lab4\\src\\main\\resources\\saturday.yaml"));

        StringBuilder data = new StringBuilder();
        while (fileReader.ready()) data.append(fileReader.readLine()).append("\n");

        System.out.println(new YAMLtoXMLConverter().convert(new YAML(data.toString())).get());

        // Теперь у вас есть дерево, которое представляет структуру YAML
        // Можете использовать его по своему усмотрению
    }
}