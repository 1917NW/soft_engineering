package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static<T> Result<T> success(){
        return new Result<T>(Code.SUCCESS, "success", null);
    }

    public static<T> Result<T> success(T data){
        return new Result<T>(Code.SUCCESS, "success", data);
    }

    public static<T> Result<T> success(T data, String message){
        return new Result<T>(Code.SUCCESS, "success", data);
    }

    public static<T> Result<T> success(String message){
        return new Result<T>(Code.SUCCESS, "Success!", null);
    }

    public static<T> Result<T> fail(){
        return new Result<T>(Code.FAIL, "Fail!", null);
    }
    public static<T> Result<T> fail(String message){
        return new Result<T>(Code.FAIL, "Fail!", null);
    }
}
