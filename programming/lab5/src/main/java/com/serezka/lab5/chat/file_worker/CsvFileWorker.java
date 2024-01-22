package com.serezka.lab5.chat.file_worker;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.serezka.lab5.chat.obj.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class CsvFileWorker extends FileWorker{
    @Override
    public List<Product> read(String filePath) {
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
}
