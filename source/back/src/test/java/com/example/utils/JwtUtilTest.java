package com.example.utils;

import com.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {


    @Autowired
    JwtUtil jwtUtil;
    @Test
    void createToken() {
        User user = new User();
        user.setUserPassword("123456");
        user.setUserName("lxy");

        System.out.println(jwtUtil.createToken(user));


    }

    @Test
    void parseToken() {
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0OGQyZmM4Yy0yZTI3LTQ2NDgtYjkxOS1kNGUwYzEyYjRmN2QiLCJzdWIiOiJ7XCJ1c2VyTmFtZVwiOlwibHh5XCIsXCJ1c2VyUGFzc3dvcmRcIjpcIjEyMzQ1NlwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4NDkzMjAxOCwiZXhwIjoxNjg0OTMzODE4fQ.BQV5ROvUmn1DWJSJyl7LUmBAnsKIhs3dl1EphIH1nR4";
        User user = jwtUtil.parseToken(s, User.class);
        System.out.println(user);

    }

    @Test
    void testParseToken() {
    }
}