package com.example.controller;

import com.example.vo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class TestController {

    @RequestMapping("/exception")
    public Result<?> throwException(){
        throw new RuntimeException();

    }
}
