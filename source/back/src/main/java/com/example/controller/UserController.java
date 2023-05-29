package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;
import com.example.utils.JwtUtil;
import com.example.vo.Code;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping ("/login")
    public Result<?> login(@RequestBody User user){
        Result<Map<String,String>> res = new Result<>();
        res.setData(new HashMap<>());
        boolean success = userService.login(user, res);
        if(success) {
            res.setCode(Code.SUCCESS);
            res.setMessage("Login successfully!");
            return res;
        }
        else {
            res.setCode(Code.FAIL);
            res.setMessage("Login failed!");
            return res;
        }
    }

    @GetMapping("/info")
    public Result<?> getInfo(@RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserIdNumber,user.getUserIdNumber());
        User one = userService.getOne(userLambdaQueryWrapper);
        return Result.success(one);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        boolean success = userService.save(user);
        if(success)
            return Result.success("Register successfully!");
        else
            return Result.fail("Register Failed!");

    }

    @GetMapping("/list")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                                  @RequestParam(value="phone",required = false) String phone,
                                                  @RequestParam(value = "userIdNumber",required = false) String userIdNumber,
                                                  @RequestParam(value = "userRole", required = false) String userRole,
                                                  @RequestParam(value = "pageNo", required = false) Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username),User::getUserName, username);
        wrapper.eq(StringUtils.hasLength(phone),User::getUserPhone, phone);
        wrapper.eq(StringUtils.hasLength(userIdNumber),User::getUserIdNumber,userIdNumber);
        wrapper.eq(StringUtils.hasLength(userRole),User::getUserRole, userRole);
        wrapper.orderByDesc(User::getUserId);
        Page<User> page = new Page<>(pageNo,pageSize);
        userService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return  Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id){
        userService.removeById(id);
        return Result.success("删除用户成功");
    }

    @PostMapping("/add")
    public Result<?> addUser(@RequestBody User user){

        userService.save(user);

        return Result.success("新增用户成功");
    }

    @PutMapping
    public Result<?> updateUser(@RequestBody User user){
        user.setUserPassword(null);
        userService.updateById(user);
        return Result.success("修改用户成功");
    }



}
