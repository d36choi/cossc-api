package com.api.cossc.dto.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class QuizDeletionRequest {

    @NotNull
    private Long id;

    private QuizDeletionRequest(Long id) {
        this.id = id;
    }

    public static QuizDeletionRequest of(Long id) {
        return new QuizDeletionRequest(id);
    }
}
