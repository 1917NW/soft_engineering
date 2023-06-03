package com.example.utils;
import com.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        String s ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxY2UwMDA2NC0zN2QzLTRjM2QtYTFkMi1mZDczMzMyZGI4NTkiLCJzdWIiOiJ7XCJ1c2VyTmFtZVwiOlwibHh5XCIsXCJ1c2VyUGFzc3dvcmRcIjpcIjEyMzQ1NlwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4NTc5OTM5OCwiZXhwIjoxNjg1ODAxMTk4fQ.mUa48p3z7GS0hBjmx6vOkRNeXl-VpodNZiF2G6cQP80";
        User user = jwtUtil.parseToken(s, User.class);
        System.out.println(user);
    }
}