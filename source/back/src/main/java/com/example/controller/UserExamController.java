package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.Exam;
import com.example.entity.User;
import com.example.entity.UserExam;
import com.example.model.UserExamStatus.UserExamStatus;
import com.example.service.IExamService;
import com.example.service.IUserExamService;
import com.example.service.IUserService;
import com.example.service.impl.UserExamServiceImpl;
import com.example.utils.JwtUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/userExam")
@CrossOrigin(origins = "*")
public class UserExamController {

    @Autowired
    IExamService examService;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserExamServiceImpl userExamService;

    @Autowired
    IUserService userService;
    @GetMapping("/userExamStatuslist")
    public Result<?> getStudentExamList(@RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);

        List<Exam> exams = examService.getBaseMapper().selectList(null);

        List<UserExamStatus> collect = exams.stream().map(r -> {

            UserExamStatus userExamStatus = new UserExamStatus();
            userExamStatus.setExam(r);

            LambdaQueryWrapper<UserExam> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserExam::getExamId, r.getExamId());
            queryWrapper.eq(UserExam::getUserId, user.getUserId());
            UserExam userExam = userExamService.getBaseMapper().selectOne(queryWrapper);
            if(userExam!=null)
                userExamStatus.setStatus(1);
            else
                userExamStatus.setStatus(0);
            return userExamStatus;
        }).collect(Collectors.toList());

        return Result.success(collect, "查询用户试卷信息");
    }

    @RequestMapping("/add")
    public Result<?> addUserExam(@RequestBody Integer userExamId, @RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);
        UserExam userExam = new UserExam();
        userExam.setExamId(userExamId);
        userExam.setUserId(user.getUserId());
        userExam.setScore(-1);
        userExamService.save(userExam);
        return Result.success("报名成功!");
    }

    @RequestMapping("/myExams")
    public Result<?> getMyExamList(@RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);

        List<Exam> myExam = userExamService.getMyExam(user.getUserId());
        return Result.success(myExam);
    }

}
