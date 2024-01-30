package com.serezka.lab5.core.io.format;

import com.serezka.lab5.core.object.Product;

import java.nio.file.Path;
import java.util.List;

public interface FormatWorker {
    List<Product> readFile(String filePath);
    List<Product> readString(String str);

    void write(List<Product> products, Path filePath);
    String writeString(List<Product> products);
}
