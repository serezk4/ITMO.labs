package com.serezka.lab.core.io.format;

import java.nio.file.Path;
import java.util.Set;

public interface FormatWorker<K> {
    Set<K> readFile(String filePath);
    Set<K> readString(String str);

    void write(Set<K> products, Path filePath);
    String writeString(Set<K> products);

    void removeById(Long id, Path filePath);
}
