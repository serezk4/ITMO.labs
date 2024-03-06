package com.serezka.lab.core.database.service;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.repository.FlatRepository;
import com.serezka.lab.core.v1.io.format.FormatWorker;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlatService {
    FlatRepository flatRepository;

    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Transactional
    public Set<Flat> findAll() {
        return new HashSet<>(flatRepository.findAll());
    }

    @Transactional
    public Flat save(Flat flat) {
        return flatRepository.save(flat);
    }

    @Transactional
    public Set<Flat> findAllByUserId(Long userId) {
        return flatRepository.findAllByUserId(userId);
    }

    @Transactional
    public Set<Flat> findAllByNameAndUserId(String name, Long userId) {
        return flatRepository.findAllByNameAndUserId(name, userId);
    }

    @Transactional
    public Set<Flat> findAllByFurnitureAndUserId(boolean furniture, Long userId) {
        return flatRepository.findAllByFurnitureAndUserId(furniture, userId);
    }

    @Transactional
    public Optional<Flat> findByIdAndUserId(Long id, Long userId) {
        return flatRepository.findByIdAndUserId(id, userId);
    }

    @Transactional
    public long countAll() {
        return flatRepository.countAll();
    }

    @Transactional
    public long countAllByUserId(Long userId) {
        return flatRepository.countAllByUserId(userId);
    }

    @Transactional
    public void removeByIdAndUserId(Long id, Long userId) {
        flatRepository.removeByIdAndUserId(id, userId);
    }

    @Transactional
    public void removeAllByUserId(Long userId) {
        flatRepository.removeAllByUserId(userId);
    }
}
