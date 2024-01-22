package com.serezka.lab5.chat.file_worker;

import com.serezka.lab5.chat.obj.Product;

import java.util.List;

public abstract class FileWorker {
    public static final String MAIN_FILE = "./%t_result.csv";

    public abstract List<Product> read(String filePath);
    public List<Product> read() {
        return read(MAIN_FILE);
    }

    public abstract void write(List<Product> products, String filePath);
}
