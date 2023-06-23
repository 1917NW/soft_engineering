package com.example.model.answerModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswersModel {

    Integer examId;

    List<AnswerModel> answers;

    Integer userId;
    Integer status;

    Integer score;

}
