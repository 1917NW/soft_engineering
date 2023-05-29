package com.example.model.UserExamStatus;

import com.example.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExamStatus {
    Exam exam;
    Integer status;
}
