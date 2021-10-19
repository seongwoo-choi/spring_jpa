package com.example.bookmanager.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test() {
        User user = new User();
        user.setEmail("ccc@cccc.com");
        user.setName("ccc");
        System.out.println(">>>>" + user);
        System.out.println(">>>>" + user.getEmail());
        System.out.println(">>>>" + user.getName());
    }
}