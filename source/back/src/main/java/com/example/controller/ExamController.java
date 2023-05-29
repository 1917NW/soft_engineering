package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Exam;
import com.example.entity.Paper;
import com.example.entity.User;
import com.example.entity.UserExam;
import com.example.model.UserExamStatus.UserExamStatus;
import com.example.model.paperModel.PaperModel;
import com.example.model.paperModel.PaperModule;
import com.example.service.IExamService;
import com.example.service.IUserExamService;
import com.example.service.IUserService;
import com.example.utils.JsonUtil;
import com.example.utils.JwtUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/exam")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    IExamService examService;




    @RequestMapping("/add")
    public Result<?> addExam(@RequestBody Exam exam){
        examService.save(exam);
        return Result.success("添加考试成功");
    }

    @GetMapping("/list")
    public Result<Map<String,Object>> getExamList(@RequestParam(value = "examName",required = false) String examName,
                                                   @RequestParam(value="examId",required = false) String examId,
                                                   @RequestParam(value = "pageNo", required = false) Long pageNo,
                                                   @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Exam> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(examName),Exam::getExamName, examName);
        wrapper.eq(StringUtils.hasLength(examId),Exam::getExamName, examId);

        wrapper.orderByDesc(Exam::getExamId);
        Page<Exam> page = new Page<>(pageNo,pageSize);
        examService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords());

        return Result.success(data);
    }

    @DeleteMapping("/{id}")
    public Result<?> deletePaperById(@PathVariable("id") Integer id){
        System.out.println(id);
        examService.removeById(id);
        return Result.success("删除用户成功");
    }

    @GetMapping("/{id}")
    public Result<Exam> getExamById(@PathVariable("id") Integer id){
        Exam exam = examService.getById(id);
        return  Result.success(exam);
    }



}
