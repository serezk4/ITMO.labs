package com.serezka.lab.core.database.service;

import com.serezka.lab.core.database.model.Person;
import com.serezka.lab.core.database.model.Product;
import com.serezka.lab.core.database.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
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
