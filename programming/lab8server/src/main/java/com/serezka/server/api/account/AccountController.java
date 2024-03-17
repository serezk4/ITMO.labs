package com.serezka.server.api.account;

import com.serezka.server.database.model.authorization.User;
import com.serezka.server.database.service.authorization.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AccountController {
    UserService userService;

    @PostMapping("/register")
    public User register(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {
        return userService.save(username, password);
    }
}
