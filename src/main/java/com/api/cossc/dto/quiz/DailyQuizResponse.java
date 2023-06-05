package com.api.cossc.dto.quiz;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public record DailyQuizResponse(List<QuizResponse> quizResponses) {

    @Builder
    public DailyQuizResponse {
    }
}
