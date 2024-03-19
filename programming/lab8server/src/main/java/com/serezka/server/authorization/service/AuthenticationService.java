package com.serezka.server.authorization.service;

import com.serezka.server.authorization.database.model.AuthenticationResponse;
import com.serezka.server.authorization.database.model.User;
import com.serezka.server.authorization.database.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ru6ik
 * Сервис для регистрации пользователя и его аутентификации.
 */

@Service
public class AuthenticationService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Присваивание пользователю требуемых полей
     */

    public AuthenticationResponse register(User request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return new AuthenticationResponse(jwtService.generateToken(repository.save(request)));

    }

    /**
     * Поле для аутентификации пользователя
     * 
     */

    public AuthenticationResponse authenticate(String username, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = repository.findByUsername(username).orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

}
