package com.api.cossc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class DailyQuizResponse {

    private final List<QuizResponse> quizResponses;

    @Builder
    public DailyQuizResponse(List<QuizResponse> quizResponses) {
        this.quizResponses = quizResponses;
    }
}
