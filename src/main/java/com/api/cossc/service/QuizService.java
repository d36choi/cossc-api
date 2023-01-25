package com.api.cossc.service;

import com.api.cossc.dto.quiz.DailyQuizRequest;
import com.api.cossc.dto.quiz.DailyQuizResponse;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest);

    DailyQuizResponse create();
}
