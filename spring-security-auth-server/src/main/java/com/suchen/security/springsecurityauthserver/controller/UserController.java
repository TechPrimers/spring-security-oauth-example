package com.suchen.security.springsecurityauthserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        User user1 = new User();
        user1.setName("user1");
        User user2 = new User();
        user2.setName("user2");
        users.add(user1);
        users.add(user2);
    }

    @GetMapping("/users")
    public List<User> users() {
        return users;
    }

    @GetMapping("/user/{name}")
    public User user(@PathVariable("name") String name) {
        return users.stream().filter((user) -> user.getName().equals(name)).findFirst().orElse(null);
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
