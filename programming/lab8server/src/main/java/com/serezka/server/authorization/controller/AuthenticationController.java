package com.serezka.server.authorization.controller;

import com.serezka.server.authorization.database.model.AuthenticationResponse;
import com.serezka.server.authorization.database.model.User;
import com.serezka.server.authorization.database.service.UserService;
import com.serezka.server.authorization.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {
    AuthenticationService authService;
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestParam("mail") String mail,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        if (userService.existsByMail(mail))
            return ResponseEntity.ok()
                    .body(new AuthenticationResponse(true,"user with this mail already registered"));

        if (userService.existsByUsername(username))
            return ResponseEntity.ok()
                    .body(new AuthenticationResponse(true,"user with this username already exists"));

        try {
            return ResponseEntity.ok(authService.register(User.builder()
                    .mail(mail)
                    .username(username)
                    .password(password)
                    .build()));
        } catch (Exception ex) {
            return ResponseEntity.ok()
                    .body(new AuthenticationResponse(true, ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        try {

            return ResponseEntity.ok(authService.authenticate(username, password));
        } catch (Exception ex) {
            return ResponseEntity.ok(new AuthenticationResponse(true, "incorrect username/password"));
        }
    }

}
