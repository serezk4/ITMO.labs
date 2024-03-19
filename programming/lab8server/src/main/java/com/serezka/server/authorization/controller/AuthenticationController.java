package com.serezka.server.authorization.controller;

import com.serezka.server.authorization.database.model.AuthenticationResponse;
import com.serezka.server.authorization.database.model.User;
import com.serezka.server.authorization.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestParam("mail") String mail,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        return ResponseEntity.ok(authService.register(User.builder()
                .mail(mail)
                .username(username)
                .password(password)
                .build()));
    }

    // faq изменил в целом регистрацию / авторизацию на только по username & password (+ mail в регистрации)
    // теперь в entity User по дефолту стоит роль 'User'

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(authService.authenticate(username, password));
    }

}
