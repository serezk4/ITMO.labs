package com.serezka.lab.core.database.repository;

import com.serezka.lab.core.database.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
    boolean existsByToken(String token);
}
