package com.example.model.paperModel;

import com.example.model.paperModel.PaperQuestion;

import java.util.List;

public class PaperModule {
    private String moduleName;
    private Integer questionTyep;

    List<PaperQuestion> questions;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getQuestionTyep() {
        return questionTyep;
    }

    public void setQuestionTyep(Integer questionTyep) {
        this.questionTyep = questionTyep;
    }

    public List<PaperQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PaperQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "PaperModule{" +
                "moduleName='" + moduleName + '\'' +
                ", questionTyep=" + questionTyep +
                ", questions=" + questions +
                '}';
    }
}
