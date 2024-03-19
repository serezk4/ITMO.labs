package com.serezka.server.collection.database.service;

import com.serezka.server.authorization.database.model.User;
import com.serezka.server.collection.database.model.Flat;
import com.serezka.server.collection.database.repository.FlatRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlatService {
    FlatRepository flatRepository;

    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Transactional
    public List<Flat> findAll() {
        return flatRepository.findAll();
    }

    @Transactional
    public Flat save(Flat flat, User user) {
        flat.setUser(user);
        return flatRepository.save(flat);
    }

    @Transactional
    public List<Flat> saveAll(List<Flat> flats, User user) {
        flats.forEach(flat -> flat.setUser(user));
        return flatRepository.saveAll(flats);
    }

    @Transactional
    public List<Flat> findAllByUserId(Long userId) {
        return flatRepository.findAllByUserId(userId);
    }

    @Transactional
    public List<Flat> findAllByNameAndUserId(String name, Long userId) {
        return flatRepository.findAllByNameAndUserId(name, userId);
    }

    @Transactional
    public List<Flat> findAllByFurnitureAndUserId(boolean furniture, Long userId) {
        return flatRepository.findAllByFurnitureAndUserId(furniture, userId);
    }

    @Transactional
    public Optional<Flat> findByIdAndUserId(Long id, Long userId) {
        return flatRepository.findByIdAndUserId(id, userId);
    }

    @Transactional
    public long count() {
        return flatRepository.count();
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
