package com.example.model.questionModel;

import java.util.List;

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

    //语句陈述题目，选词题
    private List<String> statements;


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public List<SubQuestion> getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(List<SubQuestion> subQuestions) {
        this.subQuestions = subQuestions;
    }

    public List<String> getStatements() {
        return statements;
    }

    public void setStatements(List<String> statements) {
        this.statements = statements;
    }

    public String getQuestionCorrect() {
        return questionCorrect;
    }

    public void setQuestionCorrect(String questionCorrect) {
        this.questionCorrect = questionCorrect;
    }

    public String getAnalysisTotal() {
        return analysisTotal;
    }

    public void setAnalysisTotal(String analysisTotal) {
        this.analysisTotal = analysisTotal;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "questionId=" + questionId +
                ", questionType=" + questionType +
                ", article='" + article + '\'' +
                ", subQuestions=" + subQuestions +
                ", statements=" + statements +
                ", questionCorrect='" + questionCorrect + '\'' +
                ", analysisTotal='" + analysisTotal + '\'' +
                ", score=" + score +
                '}';
    }
}
