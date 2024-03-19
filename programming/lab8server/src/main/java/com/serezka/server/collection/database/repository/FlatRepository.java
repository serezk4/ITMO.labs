package com.serezka.server.collection.database.repository;

import com.serezka.server.collection.database.model.Flat;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    @NonNull List<Flat> findAll();
    @NonNull List<Flat> findAllByUserId(Long userId);
    @NonNull List<Flat> findAllByNameAndUserId(String name, Long userId);
    @NonNull List<Flat> findAllByFurnitureAndUserId(boolean furniture, Long userId);

    @NonNull Optional<Flat> findByIdAndUserId(Long id, Long userId);

    long countAllByUserId(Long userId);

    void removeByIdAndUserId(Long id, Long userId);
    void removeAllByUserId(Long userId);
}
