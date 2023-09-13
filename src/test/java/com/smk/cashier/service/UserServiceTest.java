package com.smk.cashier.service;

import com.smk.cashier.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @org.junit.jupiter.api.Test
    void getUserList() {
        List<User> userList = UserService.getInstance().getUserList();
        assertEquals(userList.size(), 1);
    }

    @org.junit.jupiter.api.Test
    void addUser() {
        User user = new User();
        user.setUsername("Bina Informatika");
        user.setPassword("12345");

        UserService.getInstance().addUser(user);
    }
}