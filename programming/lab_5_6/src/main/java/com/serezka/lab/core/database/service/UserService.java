package com.serezka.lab.core.database.service;

import com.serezka.lab.core.database.model.User;
import com.serezka.lab.core.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    @Transactional
    public boolean existsByUsernameAndPassword(String username, String password) {
        return userRepository.existsByUsernameAndHashPassword(username, password.hashCode());
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndHashPassword(username, password.hashCode());
    }
}
