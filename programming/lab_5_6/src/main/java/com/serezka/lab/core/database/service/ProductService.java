package com.serezka.lab.core.database.service;

import com.serezka.lab.core.database.model.Person;
import com.serezka.lab.core.database.model.Product;
import com.serezka.lab.core.database.repository.ProductRepository;
import com.serezka.lab.core.io.format.FormatWorker;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    FormatWorker formatWorker;
    Path file;

    public ProductService(ProductRepository productRepository,
                          @Qualifier("csvFormatWorker") FormatWorker formatWorker,
                          @Qualifier("rootFile") Path file) {
        this.productRepository = productRepository;
        this.formatWorker = formatWorker;

        this.file = file;
    }

    @Transactional
    public Product save(Product product) {
        formatWorker.write(Collections.singletonList(product), file);
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> findAllByOwner(Person owner) {
        return productRepository.findAllByOwner(owner);
    }

    @Transactional
    public void removeById(Long id) {
        productRepository.removeById(id);
    }

    @Transactional
    public void removeByOwner(Person owner) {
        productRepository.removeByOwner(owner);
    }
}
