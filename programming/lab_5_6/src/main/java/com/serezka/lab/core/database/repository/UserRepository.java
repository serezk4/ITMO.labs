package com.serezka.lab.core.database.repository;

import com.serezka.lab.core.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameAndHashPassword(String username, int hashPassword);
    User findByUsernameAndHashPassword(String username, int hashPassword);
}
