package com.serezka.lab.core.database.service;

import com.serezka.lab.core.database.model.User;
import com.serezka.lab.core.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
    MessageDigest messageDigest;

    @Transactional
    public boolean existsByUsernameAndPassword(String username, String password) {
        synchronized (userRepository) {
            return userRepository.existsByUsernameAndHashPassword(username, new String(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8))));
        }
    }

    @Transactional
    public User save(String username, String password) {
        synchronized (userRepository) {
            return userRepository.save(new User(username, new String(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8)))));
        }
    }

    @Transactional
    public User findByUsernameAndPassword(String username, String password) {
        synchronized (userRepository) {
            return userRepository.findByUsernameAndHashPassword(username, new String(messageDigest.digest(password.getBytes(StandardCharsets.UTF_8))));
        }
    }
}
