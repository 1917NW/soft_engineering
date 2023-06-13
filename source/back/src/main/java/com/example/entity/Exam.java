package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zw
 * @since 2023-05-18
 */
@TableName("e_exam")
public class Exam implements Serializable {

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    private static final long serialVersionUID = 1L;

    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;

    private String examName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime examStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime examLastTime;

    private Integer examPaperId;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDateTime getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(LocalDateTime examStartTime) {
        this.examStartTime = examStartTime;
    }

    public LocalDateTime getExamLastTime() {
        return examLastTime;
    }

    public void setExamLastTime(LocalDateTime examLastTime) {
        this.examLastTime = examLastTime;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", examName='" + examName + '\'' +
                ", examStartTime=" + examStartTime +
                ", examLastTime=" + examLastTime +
                ", examPaperId=" + examPaperId +
                '}';
    }
}
