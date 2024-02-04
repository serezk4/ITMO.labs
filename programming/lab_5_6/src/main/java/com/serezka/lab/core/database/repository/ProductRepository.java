package com.serezka.lab.core.database.repository;

import com.serezka.lab.core.database.model.Person;
import com.serezka.lab.core.database.model.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Deprecated
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @NonNull List<Product> findAllByUserId(Long userId);
    @NonNull List<Product> findAllByOwnerAndUserId(Person owner, Long userId);

    @NonNull Optional<Product> findByIdAndUserId(@NonNull Long id, @NonNull Long userId);

    void removeByIdAndUserId(Long id, Long userId);
    void removeAllByOwnerAndUserId(Person owner, Long userId);
}
