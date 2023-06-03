package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("e_user_exam")
public class UserExam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_exam_id", type = IdType.AUTO)
    private Integer userExamId;

    @TableField(value = "user_id")
    private Integer userId;

    private Integer examId;


    //1表示已经参加
    //0表示未参加
    private Integer status;




}
