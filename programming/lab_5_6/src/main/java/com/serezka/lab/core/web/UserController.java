package com.serezka.lab.core.web;

import com.serezka.lab.core.database.model.User;
import com.serezka.lab.core.database.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping("/create")
    @ResponseBody
    public void create(@RequestBody AuthorizationData authorizationData) {
        userService.save(User.builder()
                .username(authorizationData.getUsername())
                .hashPassword(authorizationData.getPassword().hashCode()).build());
    }
}
