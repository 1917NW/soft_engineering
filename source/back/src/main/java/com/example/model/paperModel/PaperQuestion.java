package com.example.model.paperModel;

import com.example.model.questionModel.QuestionModel;

public class PaperQuestion {

    private String questionPrefix;

    private QuestionModel question;

    public String getQuestionPrefix() {
        return questionPrefix;
    }

    public void setQuestionPrefix(String questionPrefix) {
        this.questionPrefix = questionPrefix;
    }

    public QuestionModel getQuestion() {
        return question;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "PaperQuestion{" +
                "questionPrefix='" + questionPrefix + '\'' +
                ", question=" + question +
                '}';
    }
}
