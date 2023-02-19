package com.api.cossc.dto.choice;

import com.api.cossc.domain.QuizType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChoiceQuestionCreationRequest {

    private Long quizId;
    private QuizType quizType;

    public Long getQuizId() {
        return quizId;
    }

    public QuizType getQuizType() {
        return quizType;
    }
}
