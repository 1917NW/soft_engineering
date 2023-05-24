package com.example.model.questionModel;

import java.util.List;

public class InfoText {
    private List<SubQuestion> subQuestions;

    //语句陈述题目，选词题
    private List<String> statements;

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

    @Override
    public String toString() {
        return "InfoText{" +
                "subQuestions=" + subQuestions +
                ", statements=" + statements +
                '}';
    }
}
