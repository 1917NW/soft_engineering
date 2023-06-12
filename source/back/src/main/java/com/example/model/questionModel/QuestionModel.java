package com.example.model.questionModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {

    // 题目id
    // 主要用来识别是新增问题，还是插入问题
    private Integer questionId;

    // 题目类型
    /*
     * 1: 单选题
     * 2: 选词题
     * 3: 选段题
     * 4: 阅读理解
     * 5: 翻译题
     * 6: 作文题
     */
    private Integer questionType;

    // 文章(包括中文和英文，以及听力文章)
    private String article;

    // 提供给作文题和翻译题
    private String questionCorrect;

    // 作文题和翻译题的解析
    private String analysisTotal;

    private Integer score;

    // 单选题
    private List<SubQuestion> subQuestions;

    private List<String> corrects;

}
