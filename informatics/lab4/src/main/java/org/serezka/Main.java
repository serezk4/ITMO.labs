package org.serezka;

import org.serezka.parser.formats.XML;
import org.serezka.parser.formats.YAML;
import org.serezka.parser.parser.Converter;
import org.serezka.parser.parser.YAMLtoXMLConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        Converter<XML, YAML> yamLtoXMLConverter = new YAMLtoXMLConverter();

        JFrame frame = new JFrame();

        frame.setLayout(new GridLayout(0, 3));
        frame.setBounds(100, 100, 1000, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea xml = new JTextArea();
        xml.setFont(new Font("Calibri", Font.BOLD, 20));
        xml.setEditable(false);

        JTextArea yaml = new JTextArea();
        yaml.setText("""
                schedule:
                  day:
                    name: saturday
                    classes:
                      opd1:
                        type: practice
                        lector: Саржевский Иван Анатольевич
                        time: 10.00 - 11.30
                        classroom: 1327
                        location: Kronverksky pr. 49
                      opd2:
                        type: practice
                        lector: Саржевский Иван Анатольевич
                        time: 11.40 - 12.50
                        classroom: 1327
                        location: Kronverksky pr. 49
                      history1:
                        type: lecture
                        lector: Жиркова Галина Петровна
                        time: 13.30 - 14.50
                        classroom: not indicated
                        location: not indicated
                      history2:
                        type: practice
                        lector: Мартынова Дарья Олеговна
                        time: 13.30 - 14.50
                        classroom: not indicated
                        location: not indicated
                """);
        yaml.setFont(new Font("Calibri", Font.BOLD, 20));
        yaml.addKeyListener(new KeyListener() {
            private void action() {
                try {
                    xml.setText(yamLtoXMLConverter.convert(new YAML(yaml.getText())).get());
                } catch (Exception ex) {
                    xml.setText("error");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                action();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                action();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                action();
            }
        });


        frame.add(yaml);
        frame.add(xml);

        frame.setVisible(true);
    }
}