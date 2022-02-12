package com.suchen.security.springsecurityauthserver.controller;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class UserControllerTest {


    @Test
    public void testHello() {
        UserController userController = new UserController();
        List<UserController.User> users = userController.users();
        Assert.assertEquals(2, users.size());
    }

}