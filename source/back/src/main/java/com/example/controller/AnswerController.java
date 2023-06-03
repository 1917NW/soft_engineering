package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.*;
import com.example.model.answerModel.AnswerModel;
import com.example.model.answerModel.AnswersModel;
import com.example.model.answerModel.SubAnswer;
import com.example.model.questionModel.QuestionModel;
import com.example.model.questionModel.SubQuestion;
import com.example.model.scoreModel.ExamScoreModel;
import com.example.model.scoreModel.ScoreModel;
import com.example.service.*;
import com.example.utils.JsonUtil;
import com.example.utils.JwtUtil;
import com.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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
@RequestMapping("/answer")
@CrossOrigin(origins = "*")
public class AnswerController {

    @Autowired
    IQuestionService questionService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    IAnswerService answerService;

    @Autowired
    IExamService examService;
    Map<Integer, AnswersModel> correctPapers = new HashMap<>();

    @Autowired
    IUserService userService;
    @Autowired
    IUserExamService userExamService;

    @RequestMapping("/addAnswer")
    public Result<?> addAnswer(@RequestBody AnswersModel answersModel, @RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);
        Integer score = computeScoreExcludeEditQuestions(answersModel);
        System.out.println(score);
        Answer answerFromAnswerModel = getAnswerFromAnswerModel(answersModel);
        answerFromAnswerModel.setUserId(user.getUserId());
        answerFromAnswerModel.setStatus(0);
        answerFromAnswerModel.setScore(score);
        answerService.save(answerFromAnswerModel);

        UserExam userExam = new UserExam();
        userExam.setStatus(1);
        LambdaQueryWrapper<UserExam> userExamLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userExamLambdaQueryWrapper.eq(UserExam::getUserId, user.getUserId());
        userExamLambdaQueryWrapper.eq(UserExam::getExamId,answersModel.getExamId() );
        userExamService.update(userExam,userExamLambdaQueryWrapper);

        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> updateAnswer(@RequestBody AnswersModel answersModel){
        System.out.println(answersModel);
        Integer score = computeScoreExcludeEditQuestions(answersModel);
        System.out.println(score);
        Answer answerFromAnswerModel = getAnswerFromAnswerModel(answersModel);
        answerFromAnswerModel.setUserId(answersModel.getUserId());
        answerFromAnswerModel.setStatus(answersModel.getStatus());
        answerFromAnswerModel.setScore(score);
        LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        answerLambdaQueryWrapper.eq(Answer::getUserId,answersModel.getUserId());
        answerLambdaQueryWrapper.eq(Answer::getExamId, answerFromAnswerModel.getExamId());
        answerService.update(answerFromAnswerModel,answerLambdaQueryWrapper);
        return Result.success("批改成功");
    }

    @GetMapping("/list")
    public Result<Map<String,Object>> getAnswerList(@RequestParam(value = "examId",required = false) String examId,
                                                  @RequestParam(value = "pageNo", required = false) Long pageNo,
                                                  @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(examId),Answer::getExamId, examId);
        wrapper.orderByDesc(Answer::getUserId);
        Page<Answer> page = new Page<>(pageNo,pageSize);
        answerService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());
        data.put("rows", page.getRecords().stream().map(r -> {
            return getAnswersModelFromAnswer(r);
        }).collect(Collectors.toList()));

        return Result.success(data);
    }
    @RequestMapping("/getMyAnswer")
    public Result<?> getMyAnswer(@RequestParam("examId") String examId, @RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);
        LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        answerLambdaQueryWrapper.eq(Answer::getUserId,user.getUserId());
        answerLambdaQueryWrapper.eq(Answer::getExamId,examId);
        Answer one = answerService.getOne(answerLambdaQueryWrapper);
        AnswersModel answersModelFromAnswer = getAnswersModelFromAnswer(one);
        System.out.println(answersModelFromAnswer);
        return Result.success(answersModelFromAnswer);
    }

    @RequestMapping("/scorelist")
    public Result<?> getExamScore(@RequestParam(value = "examId",required = false) String examId,
                                  @RequestParam(value = "userIdNumber", required = false) String userIdNumber,
                                  @RequestParam(value = "pageNo", required = false) Long pageNo,
                                  @RequestParam(value = "pageSize") Long pageSize){
        User one = null;
        if(StringUtils.hasLength(userIdNumber)){
           LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
           userLambdaQueryWrapper.eq(User::getUserIdNumber, userIdNumber);
           one= userService.getOne(userLambdaQueryWrapper);
       }

        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(examId),Answer::getExamId, examId);
        if(one != null)
        wrapper.eq(StringUtils.hasLength(userIdNumber),Answer::getUserId,one.getUserId());
        Page<Answer> page = new Page<>(pageNo,pageSize);
        answerService.page(page,wrapper);
        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());

        List<ExamScoreModel> collect = page.getRecords().stream().map(r -> {
            ExamScoreModel examScoreModel = new ExamScoreModel();
            examScoreModel.setScore(r.getScore());
            examScoreModel.setStatus(r.getStatus());
            LambdaQueryWrapper<User> userLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper1.eq(User::getUserId, r.getUserId());
            User one1 = userService.getOne(userLambdaQueryWrapper1);

            examScoreModel.setUserIdNumber(one1.getUserIdNumber());
            examScoreModel.setUserName(one1.getUserName());
            return examScoreModel;
        }).collect(Collectors.toList());
        data.put("rows",collect);
        return Result.success(data);
    }
    @RequestMapping("/getExamsDone")
    public Result<?> getExamDone(@RequestHeader("X-Token") String token){
        User user = jwtUtil.parseToken(token, User.class);
        LambdaQueryWrapper<Answer> answerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        answerLambdaQueryWrapper.eq(Answer::getUserId, user.getUserId());
        List<Answer> answerlist = answerService.list(answerLambdaQueryWrapper);
        System.out.println(answerlist);
        List<ScoreModel> collect = answerlist.stream().map(a -> {
                    ScoreModel scoreModel = new ScoreModel();
                    scoreModel.setStatus(a.getStatus());
                    scoreModel.setScore(a.getScore());

                    LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<Exam>();
                    examLambdaQueryWrapper.eq(Exam::getExamId, a.getExamId());
                    Exam one = examService.getOne(examLambdaQueryWrapper);
                    scoreModel.setExam(one);
                    return scoreModel;
                }
        ).collect(Collectors.toList());

        return Result.success(collect);
    }

    public Answer getAnswerFromAnswerModel(AnswersModel answersModel){
        Answer answer = new Answer();
        answer.setExamId(answersModel.getExamId());
        String s = JsonUtil.toJsonStr(answersModel.getAnswers());
        answer.setAnswer(s);
        return answer;
    }

    public Integer getTotal(AnswersModel studentAnswer){

        return 0;
    }
    public Integer computeScoreExcludeEditQuestions(AnswersModel studentAnswer){
        AnswersModel correctModel = correctPapers.get(studentAnswer.getExamId());
        if(correctModel == null){
             correctModel = getCorrectModel(studentAnswer);
             correctPapers.put(studentAnswer.getExamId(), correctModel);
        }

        List<AnswerModel> correctAnswers = correctModel.getAnswers();
        List<AnswerModel> answers = studentAnswer.getAnswers();
        int score = 0;
        for(int i=0; i<answers.size(); i++){

            score += getScore(answers.get(i),correctAnswers.get(i));
        }
        return score;

    }

    Integer getScore(AnswerModel studentAnswer, AnswerModel correctAnswer){
        if(studentAnswer.getQuestionType() == 0 || studentAnswer.getQuestionType()==5) {
            List<SubAnswer> subanswers = studentAnswer.getSubanswers();

            for(int i = 0;i<subanswers.size();i++) {
                if(subanswers.get(i).getScore() == null) {
                    subanswers.get(i).setScore(0);
                    return 0;
                }
            }
            return subanswers.get(0).getScore();
        }
        else {
            List<SubAnswer> subanswers = studentAnswer.getSubanswers();
            List<SubAnswer> correctsubanswers = correctAnswer.getSubanswers();
            int score = 0;
            for(int i = 0; i< subanswers.size();i++){
                //
                if(subanswers.get(i).getAnswer().equals(correctsubanswers.get(i).getAnswer()) ||
                        subanswers.get(i).getAnswer().equals(correctsubanswers.get(i).getAnswer().toLowerCase())||
                                        subanswers.get(i).getAnswer().equals(correctsubanswers.get(i).getAnswer().toUpperCase())
                ){
                    subanswers.get(i).setScore(correctsubanswers.get(i).getScore());
                    score += correctsubanswers.get(i).getScore();
                }else
                    subanswers.get(i).setScore(0);
            }
            return score;
        }
    }


    public AnswersModel getAnswersModelFromAnswer(Answer answer){
        AnswersModel answersModel = new AnswersModel();
        answersModel.setExamId(answer.getExamId());
        List<AnswerModel> answerModels = JsonUtil.toJsonListObject(answer.getAnswer(), AnswerModel.class);
        answersModel.setAnswers(answerModels);
        answersModel.setStatus(answer.getStatus());
        answersModel.setScore(answer.getScore());
        answersModel.setUserId(answer.getUserId());
        return answersModel;
    }

    AnswersModel getCorrectModel(AnswersModel answersModel){
        AnswersModel correctModel = new AnswersModel();
        correctModel.setExamId(answersModel.getExamId());
        List<AnswerModel> correctAnswerModels = new ArrayList<>();
        List<AnswerModel> answers = answersModel.getAnswers();
        for(AnswerModel answerModel: answers){
            AnswerModel ans = new AnswerModel();
            ans.setQuestionId(answerModel.getQuestionId());
            ans.setQuestionType(answerModel.getQuestionType());
            setCorrectAnswer(ans);
            correctAnswerModels.add(ans);
        }
        correctModel.setAnswers(correctAnswerModels);
        return correctModel;
    }


    public void setCorrectAnswer(AnswerModel answer){
        //如果为作文题和翻译题，直接返回
        if(answer.getQuestionType() == 0 || answer.getQuestionType() ==5 )
            return;
        else if(answer.getQuestionType() ==1 || answer.getQuestionType()==4 || answer.getQuestionType() ==3){
            //听力选择题和阅读选择题
            LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Question::getQuestionId, answer.getQuestionId());
            Question one = questionService.getOne(queryWrapper);
            QuestionModel questionModel = one.getQuestionModel();
            List<SubQuestion> subQuestions = questionModel.getSubQuestions();
            List<SubAnswer> answers = new ArrayList<>();
            for(SubQuestion i : subQuestions){
                answers.add(new SubAnswer(i.getCorrect(),i.getQuestionScore()));
            }
            answer.setSubanswers(answers);


        }else if(answer.getQuestionType() ==2 ){
            //阅读选词题
            LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Question::getQuestionId, answer.getQuestionId());
            Question one = questionService.getOne(queryWrapper);
            QuestionModel questionModel = one.getQuestionModel();
            List<String> corrects = questionModel.getCorrects();
            List<SubAnswer> answers = new ArrayList<>();
            for(String str : corrects){
                answers.add(new SubAnswer(str, questionModel.getScore()));
            }
            answer.setSubanswers(answers);
        }
    }


}
