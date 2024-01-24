package com.serezka.lab5.chat.io.format;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.serezka.lab5.chat.object.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component @Log4j2
public class CsvFormatWorker implements FormatWorker {
    @Override
    public List<Product> readFile(String filePath) {
        try {
            return new CsvToBeanBuilder<>(Files.newBufferedReader(Path.of(filePath)))
                    .withType(Product.class)
                    .build().parse().stream().map(q -> (Product) q).toList();
        } catch (IOException e) {
            log.warn(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Product> readString(String csvContent) {
        try {
            return new CsvToBeanBuilder<>(new StringReader(csvContent))
                    .withType(Product.class)
                    .build().parse().stream().map(q -> (Product) q).toList();
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void write(List<Product> products, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            StatefulBeanToCsv<Product> beanToCsv = new StatefulBeanToCsvBuilder<Product>(writer)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(products);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public String writeString(List<Product> products) {
        try (Writer writer = new StringWriter()) {
            StatefulBeanToCsv<Product> beanToCsv = new StatefulBeanToCsvBuilder<Product>(writer)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(products);
            return writer.toString();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.warn(e.getMessage());
            return "";
        }
    }
}
