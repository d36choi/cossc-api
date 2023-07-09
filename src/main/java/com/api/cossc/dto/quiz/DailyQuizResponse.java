package com.api.cossc.dto.quiz;

import lombok.Builder;

import java.util.List;

public record DailyQuizResponse(List<QuizResponse> quizResponses) {

    @Builder
    public DailyQuizResponse {
    }
}
