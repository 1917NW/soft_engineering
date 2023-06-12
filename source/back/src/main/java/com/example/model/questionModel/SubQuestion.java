package com.example.model.questionModel;

import com.example.model.questionModel.QuestionItemObject;

import java.util.List;

public class SubQuestion {
    private String questionTitle;

    private List<QuestionItemObject> items;

    private Integer questionScore;

    private String analysis;

    // 这里遇到了一个小问题，字段名不能为大写，否则json转换时会出现问题
    private String correct;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public List<QuestionItemObject> getItems() {
        return items;
    }

    public void setItems(List<QuestionItemObject> items) {
        this.items = items;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "SubQuestion{" +
                "questionTitle='" + questionTitle + '\'' +
                ", items=" + items +
                ", questionScore=" + questionScore +
                ", analysis='" + analysis + '\'' +
                ", correct='" + correct + '\'' +
                '}';
    }
}
