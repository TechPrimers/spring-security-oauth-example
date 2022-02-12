package com.suchen.security.springsecurityauthserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @GetMapping
    public List<User> hello() {
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user2.setName("user2");
        return Arrays.asList(user1, user2);
    }


    static class User {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name;
    }
}
