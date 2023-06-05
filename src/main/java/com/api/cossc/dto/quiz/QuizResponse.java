package com.api.cossc.dto.quiz;

import com.api.cossc.domain.QuizEntity;
import lombok.Builder;
import lombok.Getter;

@Getter

public class QuizResponse {

    private final Long id;
    private final String title;
    private final String desc;
    private final String type;

    private final boolean correct;
    private final boolean solved;

    @Builder
    public QuizResponse(Long id, String title, String desc, String type, boolean correct, boolean solved) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.correct = correct;
        this.solved = solved;
    }

    public QuizResponse(QuizResponse quizResponse) {
        this.id = quizResponse.id;
        this.title = quizResponse.title;
        this.desc = quizResponse.desc;
        this.type = quizResponse.type;
        this.correct = quizResponse.correct;
        this.solved = quizResponse.solved;
    }

    public static QuizResponse of(QuizEntity quizEntity) {
        return QuizResponse.builder()
                .id(quizEntity.getId())
                .title(quizEntity.getTitle())
                .desc(quizEntity.getDescription())
                .type(quizEntity.getType().getName())
                .solved(false)
                .correct(false)
                .build();
    }
}
