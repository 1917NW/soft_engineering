package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxy
 * @since 2023-05-11
 * 表示学生和考试之间的报名信息和考试成绩
 */
@TableName("e_user_exam")
public class UserExam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_exam_id", type = IdType.AUTO)
    private Integer userExamId;

    @TableField(value = "user_id")
    private Integer userId;

    private Integer examId;

    //-1表示还没有成绩
    private Integer score;

    public Integer getUserExamId() {
        return userExamId;
    }

    public void setUserExamId(Integer userExamId) {
        this.userExamId = userExamId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserExam{" +
            "userExamId=" + userExamId +
            ", userId=" + userId +
            ", examId=" + examId +
            ", score=" + score +
        "}";
    }
}
