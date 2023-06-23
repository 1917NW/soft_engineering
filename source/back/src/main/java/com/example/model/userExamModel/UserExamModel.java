package com.example.model.userExamModel;

import com.example.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExamModel {
    private Exam exam;
    private Integer status;

}
