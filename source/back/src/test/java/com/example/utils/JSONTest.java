package com.example.utils;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JSONTest {

    @Test
    public void test(){
        List<String> s = new ArrayList<>();
        s.add("A");
        s.add("B");
        String s1 = JSON.toJSONString(s);
        System.out.println(s1);
        List<String> strings = JsonUtil.toJsonListObject(s1, String.class);
        System.out.println(strings);

    }
}
