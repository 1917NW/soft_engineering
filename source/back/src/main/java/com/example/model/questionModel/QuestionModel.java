package com.example.model.questionModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {

    //题目id，主要用来识别是新增问题，还是插入问题
    private Integer questionId;

    //题目类型，1为单选题，2为选词题，3为选段题，4为阅读理解，5为翻译题，6为作文题
    private Integer questionType;


    //文章(包括中文和英文，以及听力文章)
    private String article;


    //提供给作文题和翻译题
    private String questionCorrect;

    //作文题和翻译题的解析
    private String analysisTotal;

    private Integer score;

    //单选题
    private List<SubQuestion> subQuestions;

    //
    private List<String> corrects;



}
