package com.serezka.server.authorization.database.service;

import com.serezka.server.authorization.database.model.User;
import com.serezka.server.authorization.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public List<User> findAllByUsernameContaining(String usernamePart) {
        return userRepository.findAllByUsernameContaining(usernamePart);
    }

    @Transactional
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public boolean existsByMail(String mail) {
        return userRepository.existsByMail(mail);
    }

    @Transactional
    public boolean existsByMailOrUsername(String mail, String username) {
        return userRepository.existsByMailOrUsername(mail, username);
    }
}
