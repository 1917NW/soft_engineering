package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Paper;
import com.example.entity.Question;
import com.example.entity.User;
import com.example.model.paperModel.PaperModel;
import com.example.model.paperModel.PaperModule;
import com.example.model.questionModel.QuestionModel;
import com.example.service.IPaperService;
import com.example.service.impl.PaperServiceImpl;
import com.example.utils.JsonUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zw
 * @since 2023-05-17
 */
@RestController
@RequestMapping("/paper")
@CrossOrigin(origins = "*")
public class PaperController {

    @Autowired
    IPaperService paperService;

    Paper getPaperFromPaperModel(PaperModel paperModel) {
        Paper paper = new Paper();
        paper.setPaperId(paperModel.getPaperId());
        paper.setPaperName(paperModel.getPaperName());
        paper.setPaperQuestionList(JsonUtil.toJsonStr(paperModel.getPaperQuestions()));
        return paper;
    }

    @RequestMapping("/add")
    public Result<?> addPaper(@RequestBody PaperModel paperModel) {
        System.out.println(paperModel);
        Paper paper = new Paper();
        paper.setPaperId(paperModel.getPaperId());
        paper.setPaperName(paperModel.getPaperName());
        paper.setPaperQuestionList(JsonUtil.toJsonStr(paperModel.getPaperQuestions()));
        paperService.save(paper);

        return Result.success();
    }

    @GetMapping("/list")
    public Result<Map<String, Object>> getPaperList(
            @RequestParam(value = "paperName", required = false) String paperName,
            @RequestParam(value = "paperId", required = false) String paperId,
            @RequestParam(value = "pageNo", required = false) Long pageNo,
            @RequestParam(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<Paper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(paperName), Paper::getPaperName, paperName);
        wrapper.eq(StringUtils.hasLength(paperId), Paper::getPaperId, paperId);

        wrapper.orderByDesc(Paper::getPaperId);
        Page<Paper> page = new Page<>(pageNo, pageSize);
        paperService.page(page, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords().stream().map(r -> {
            PaperModel paperModel = new PaperModel();
            paperModel.setPaperName(r.getPaperName());
            paperModel.setPaperId(r.getPaperId());
            paperModel.setPaperQuestions(JsonUtil.toJsonListObject(r.getPaperQuestionList(), PaperModule.class));
            return paperModel;
        }).collect(Collectors.toList()));

        return Result.success(data);
    }

    @DeleteMapping("/{id}")
    public Result<?> deletePaperById(@PathVariable("id") Integer id) {
        System.out.println(id);
        paperService.removeById(id);
        return Result.success("删除试卷成功");
    }

    @PutMapping
    public Result<?> updatePaper(@RequestBody PaperModel pm) {
        Paper paper = getPaperFromPaperModel(pm);
        paperService.updateById(paper);
        return Result.success("修改试卷成功");
    }

    @GetMapping("/{id}")
    public Result<Paper> getPaperById(@PathVariable("id") Integer id) {
        Paper paper = paperService.getById(id);
        System.out.println(paper);
        return Result.success(paper);
    }

}
