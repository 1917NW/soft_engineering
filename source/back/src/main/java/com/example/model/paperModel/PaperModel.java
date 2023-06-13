package com.example.model.paperModel;

import com.example.model.paperModel.PaperModule;

import java.util.List;


public class PaperModel {

    private Integer paperId;

    private String paperName;

    List<PaperModule> paperQuestions;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paparId) {
        this.paperId = paparId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public List<PaperModule> getPaperQuestions() {
        return paperQuestions;
    }

    public void setPaperQuestions(List<PaperModule> paperQuestions) {
        this.paperQuestions = paperQuestions;
    }

    @Override
    public String toString() {
        return "PaperModel{" +
                "papaerId=" + paperId +
                ", paperName='" + paperName + '\'' +
                ", paperQuestions=" + paperQuestions +
                '}';
    }
}
