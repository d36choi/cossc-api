package com.api.cossc.dto;

import com.api.cossc.domain.QuizEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter

public class QuizResponse {

    private final Long id;
    private final String title;
    private final String desc;
    private final String type;

    @Builder
    public QuizResponse(Long id, String title, String desc, String type) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.type = type;
    }

    public QuizResponse(QuizResponse quizResponse) {
        this.id = quizResponse.id;
        this.title = quizResponse.title;
        this.desc = quizResponse.desc;
        this.type = quizResponse.type;
    }

    public static QuizResponse of(QuizEntity quizEntity) {
        return QuizResponse.builder()
                .id(quizEntity.getId())
                .title(quizEntity.getTitle())
                .desc(quizEntity.getDescription())
                .type(quizEntity.getType().getName())
                .build();
    }
}
