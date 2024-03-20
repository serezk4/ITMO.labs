package com.serezka.server.authorization.database.repository;

import com.serezka.server.authorization.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findAllByUsernameContaining(String usernamePart);

    boolean existsByUsername(String username);

    boolean existsByMail(String mail);

    boolean existsByMailOrUsername(String mail, String username);
}
