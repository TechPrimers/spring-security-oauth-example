package com.suchen.security.springsecurityauthserver.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class UserControllerTest {


    @Test
    public void testHello() {
        UserController userController = new UserController();
        List<UserController.User> users = userController.users();
        Assertions.assertEquals(2, users.size());
    }

}