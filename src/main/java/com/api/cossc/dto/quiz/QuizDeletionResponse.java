package com.api.cossc.dto.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizDeletionResponse {

    private Long id;

    private QuizDeletionResponse(Long id) {
        this.id = id;
    }

    public static QuizDeletionResponse of(Long id) {
        return new QuizDeletionResponse(id);
    }
}
