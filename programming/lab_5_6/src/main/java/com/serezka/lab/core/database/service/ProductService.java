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
    Path filePath;

    public ProductService(ProductRepository productRepository,
                          @Qualifier("csvFormatWorker") FormatWorker formatWorker,
                          @Qualifier("rootFile") Path filePath) {
        this.productRepository = productRepository;
        this.formatWorker = formatWorker;

        this.filePath = filePath;
    }

    @Transactional
    public Product save(Product product) {
        formatWorker.write(Collections.singletonList(product), filePath);
        return productRepository.save(product);
    }

    @Transactional
    public List<Product> findAllByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Transactional
    public List<Product> findAllByOwnerAndUserId(Person owner, Long userId) {
        return productRepository.findAllByOwnerAndUserId(owner, userId);
    }

    @Transactional
    public void removeByIdAndUserId(Long id, Long userId) {
        formatWorker.removeById(id, filePath);
        productRepository.removeByIdAndUserId(id, userId);
    }

    @Transactional
    public void removeByOwnerAndUserId(Person owner, Long userId) {
        productRepository.removeAllByOwnerAndUserId(owner, userId);
    }
}
