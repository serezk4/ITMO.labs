package com.serezka.lab.core.v2.api.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.github.f4b6a3.uuid.UuidCreator;
import com.serezka.lab.core.database.model.Token;
import com.serezka.lab.core.database.service.TokenService;
import com.serezka.lab.core.database.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/authorization")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthorizationController {
    UserService userService;
    TokenService tokenService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "login", required = true) String login,
                        @RequestParam(value = "password", required = true) String password) {
        if (!userService.existsByUsernameAndPassword(login, password))
            return "invalid login or password";

        return tokenService.save(new Token(userService.findByUsernameAndPassword(login, password))).getToken();
    }
}
