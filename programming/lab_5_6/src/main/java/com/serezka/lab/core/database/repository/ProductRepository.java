package com.serezka.lab.core.database.repository;

import com.serezka.lab.core.database.model.Person;
import com.serezka.lab.core.database.model.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @NonNull List<Product> findAll();
    @NonNull List<Product> findAllByOwner(Person owner);

    @NonNull Optional<Product> findById(@NonNull Long id);

    void removeById(Long id);
    void removeByOwner(Person owner);
}
