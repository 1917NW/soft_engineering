package com.example.model.answerModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerModel {

    Integer questionId;
    Integer questionType;

    String statementAnswer;

    List<SubAnswer> subanswers;



}
