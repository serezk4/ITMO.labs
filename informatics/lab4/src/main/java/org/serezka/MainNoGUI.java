package org.serezka;

import org.serezka.parser.formats.XML;
import org.serezka.parser.formats.YAML;
import org.serezka.parser.parser.Converter;
import org.serezka.parser.parser.YAMLtoXMLConverter;

import java.io.*;

public class MainNoGUI {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\serezk4\\IdeaProjects\\ITMO.labs\\programming\\ITMO.labs\\informatics\\lab4\\src\\main\\resources\\saturday.yaml"));
        BufferedWriter consoleOut = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("C:\\Users\\serezk4\\IdeaProjects\\ITMO.labs\\programming\\ITMO.labs\\informatics\\lab4\\src\\main\\resources\\out.xml"));

        StringBuilder fileData = new StringBuilder();
        while (fileReader.ready()) fileData.append(fileReader.readLine()).append("\n");

        Converter<XML, YAML> yamlToXml = new YAMLtoXMLConverter();

        final String result = yamlToXml.convert(new YAML(fileData.toString())).get();

        consoleOut.append(result).flush();
        fileWriter.append(result).flush();

        fileReader.close();
        consoleOut.close();
        fileWriter.close();
    }
}
