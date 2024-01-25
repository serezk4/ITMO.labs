package com.serezka.lab6server.handler.io.format;


import com.serezka.lab6server.handler.object.Product;

import java.nio.file.Path;
import java.util.List;

public interface FormatWorker {
    List<Product> readFile(String filePath);
    List<Product> readString(String str);

    void write(List<Product> products, Path filePath);
    String writeString(List<Product> products);
}
