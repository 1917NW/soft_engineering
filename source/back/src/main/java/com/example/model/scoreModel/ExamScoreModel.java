package com.example.model.scoreModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamScoreModel {
    private Integer userIdNumber;
    private String userName;

    private Integer status;

    private Integer score;
}
