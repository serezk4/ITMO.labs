package com.serezka.lab.core.database.service;

import com.serezka.lab.core.database.model.Token;
import com.serezka.lab.core.database.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TokenService {
    TokenRepository tokenRepository;

    @Transactional
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Transactional
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Transactional
    public boolean existsByToken(String token) {
        return tokenRepository.existsByToken(token);
    }
}
