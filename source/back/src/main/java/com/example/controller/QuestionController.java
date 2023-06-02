package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Question;
import com.example.entity.User;
import com.example.model.questionModel.InfoText;
import com.example.model.questionModel.QuestionModel;
import com.example.service.IQuestionService;
import com.example.utils.JsonUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxy
 * @since 2023-05-14
 */
@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "*")
public class QuestionController {

    @Autowired
    IQuestionService questionService;

    @RequestMapping("/add")
    public Result<?> addQuestion(@RequestBody QuestionModel qm){

        System.out.println(qm);
        Question question = getQuestionFromQuestionModel(qm);
        questionService.save(question);
        return Result.success("添加试题成功");
    }

    public  Question getQuestionFromQuestionModel(QuestionModel qm) {
        Question question = new Question();
        question.setQuestionId(qm.getQuestionId());
        question.setQuestionScore(qm.getScore());
        question.setQuestionTitle(qm.getArticle());
        question.setQuestionType(qm.getQuestionType());
        question.setQuestionCorrect(qm.getQuestionCorrect());
        question.setQuestionAnalysisTotal(qm.getAnalysisTotal());


        InfoText infoText = new InfoText();
        infoText.setSubQuestions(qm.getSubQuestions());
        infoText.setStatements(qm.getCorrects());

        question.setQuestionDescription(JsonUtil.toJsonStr(infoText));
        return question;
    }



    @GetMapping("/list")
    public Result<Map<String,Object>> getQuestionList(@RequestParam(value = "questionId",required = false) String questionId,
                                                  @RequestParam(value="questionType",required = false) String questionType,
                                                  @RequestParam(value = "pageNo", required = false) Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(questionId),Question::getQuestionId, questionId);
        wrapper.eq(StringUtils.hasLength(questionType),Question::getQuestionType, questionType);

        Page<Question> page = new Page<>(pageNo,pageSize);


        questionService.page(page, wrapper);
        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords().stream().map(r -> {

            QuestionModel questionModel = new QuestionModel();
            questionModel.setQuestionId(r.getQuestionId());
            questionModel.setQuestionCorrect(r.getQuestionCorrect());
            questionModel.setQuestionType(r.getQuestionType());
            questionModel.setArticle(r.getQuestionTitle());
            questionModel.setScore(r.getQuestionScore());
            InfoText infoText = JsonUtil.toJsonObject(r.getQuestionDescription(), InfoText.class);
            questionModel.setSubQuestions(infoText.getSubQuestions());
            questionModel.setCorrects(infoText.getStatements());
            questionModel.setAnalysisTotal(r.getQuestionAnalysisTotal());

            //questionModel.setSubQuestions();
            return questionModel;
        } ).collect(Collectors.toList()));

        return Result.success(data);
    }

    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id){
        questionService.removeById(id);
        return Result.success("删除试题成功");
    }

    @PutMapping
    public Result<?> updateQuestion(@RequestBody QuestionModel qm){
        Question question = getQuestionFromQuestionModel(qm);
        questionService.updateById(question);
        return Result.success("修改试题成功");
    }

    @GetMapping("/{id}")
    public Result<Question> getQuestionById(@PathVariable("id") Integer id){
        Question question = questionService.getById(id);
        System.out.println(question);
        return  Result.success(question);
    }

}
