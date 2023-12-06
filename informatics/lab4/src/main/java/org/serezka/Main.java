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

        frame.setLayout(new GridLayout(0, 4));
        frame.setBounds(100, 100, 1000, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea xml = new JTextArea();
        xml.setFont(new Font("Calibri", Font.BOLD, 20));
        xml.setEditable(false);
        xml.setBounds(0,0,300,600);


        JTextArea yaml = new JTextArea();
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