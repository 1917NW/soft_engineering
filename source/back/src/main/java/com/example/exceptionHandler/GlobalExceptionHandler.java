package com.example.exceptionHandler;

import com.example.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Executable;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result<?> exceptionHandler(HttpServletRequest req, Exception e){
        return Result.fail("服务器繁忙, 请稍后再试");
    }
}
