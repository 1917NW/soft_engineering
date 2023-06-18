package com.example.model.scoreModel;

import com.example.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreModel {

    private Exam exam;

    private Integer status;

    private Integer score;

}
