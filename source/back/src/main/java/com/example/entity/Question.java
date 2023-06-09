package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.model.questionModel.InfoText;
import com.example.model.questionModel.QuestionModel;
import com.example.utils.JsonUtil;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2023-05-16
 */
@TableName("e_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_id", type = IdType.AUTO)

    /* ID */
    private Integer questionId;
    /* 标题 */
    private String questionTitle;
    /* 类型 */
    private Integer questionType;
    /* 描述 */
    private String questionDescription;
    /* 分数 */
    private Integer questionScore;
    /* 正确答案 */
    private String questionCorrect;
    /* 指导 */
    private String questionAnalysisTotal;

    public String getQuestionAnalysisTotal() {
        return questionAnalysisTotal;
    }

    public void setQuestionAnalysisTotal(String questionAnalysisTotal) {
        this.questionAnalysisTotal = questionAnalysisTotal;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    public String getQuestionCorrect() {
        return questionCorrect;
    }

    public void setQuestionCorrect(String questionCorrect) {
        this.questionCorrect = questionCorrect;
    }

    public QuestionModel getQuestionModel() {
        QuestionModel questionModel = new QuestionModel();

        questionModel.setQuestionId(getQuestionId());
        questionModel.setQuestionCorrect(getQuestionCorrect());
        questionModel.setQuestionType(getQuestionType());
        questionModel.setArticle(getQuestionTitle());
        questionModel.setScore(getQuestionScore());
        InfoText infoText = JsonUtil.toJsonObject(getQuestionDescription(), InfoText.class);
        questionModel.setSubQuestions(infoText.getSubQuestions());
        questionModel.setCorrects(infoText.getStatements());
        questionModel.setAnalysisTotal(getQuestionAnalysisTotal());
        return questionModel;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionType=" + questionType +
                ", questionDescription='" + questionDescription + '\'' +
                ", questionScore=" + questionScore +
                ", questionCorrect='" + questionCorrect + '\'' +
                ", questionAnalysisTotal='" + questionAnalysisTotal + '\'' +
                '}';
    }
}
