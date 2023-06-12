package com.example.model.questionModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoText {
    private List<SubQuestion> subQuestions;

    // 语句陈述题目，选词题
    private List<String> statements;

}
