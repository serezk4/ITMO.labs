package com.serezka.lab.core.io.format.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.io.format.FormatWorker;
import com.serezka.lab.core.database.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log4j2
public class CsvFormatWorker implements FormatWorker<Flat> {
    @Override
    public Set<Flat> readFile(String filePath) {
        try {
            return new CsvToBeanBuilder<>(Files.newBufferedReader(Path.of(filePath)))
                    .withType(Product.class)
                    .build().parse().stream().map(q -> (Flat) q).collect(Collectors.toSet());
        } catch (IOException e) {
            log.warn(e.getMessage());
            return Collections.emptySet();
        }
    }

    @Override
    public Set<Flat> readString(String csvContent) {
        try {
            return new CsvToBeanBuilder<>(new StringReader(csvContent))
                    .withType(Product.class)
                    .build().parse().stream().map(q -> (Flat) q).collect(Collectors.toSet());
        } catch (Exception e) {
            log.warn(e.getMessage());
            return Collections.emptySet();
        }
    }

    @Override
    public void write(Set<Flat> flats, Path filePath) {
        try (Writer writer = new FileWriter(filePath.toFile(), true)) {
            StatefulBeanToCsv<Flat> beanToCsv = new StatefulBeanToCsvBuilder<Flat>(writer)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(flats.stream().toList());
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public String writeString(Set<Flat> flats) {
        try (Writer writer = new StringWriter()) {
            StatefulBeanToCsv<Flat> beanToCsv = new StatefulBeanToCsvBuilder<Flat>(writer)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(flats.stream().toList());
            return writer.toString();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            log.warn(e.getMessage());
            return "";
        }
    }

    @Override
    public void removeById(Long id, Path filePath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            while (fileReader.ready()) {
                final String line = fileReader.readLine();
                if (line.startsWith("\"" + id + "\"")) continue;
                fileWriter.write(line);
                fileWriter.write("\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }
}
