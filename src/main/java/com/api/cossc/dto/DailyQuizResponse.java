package com.api.cossc.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class DailyQuizResponse {

    private List<QuizResponse> quizResponses;

    @Builder
    public DailyQuizResponse(List<QuizResponse> quizResponses) {
        this.quizResponses = quizResponses;
    }
}
