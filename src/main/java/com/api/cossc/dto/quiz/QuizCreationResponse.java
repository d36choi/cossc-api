package com.api.cossc.dto.quiz;

import com.api.cossc.domain.QuizEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class QuizCreationResponse {

    private Long id;

    private QuizCreationResponse(Long id) {
        this.id = id;
    }

    public static QuizCreationResponse of(QuizEntity quizEntity) {
        return new QuizCreationResponse(quizEntity.getId());
    }
}
