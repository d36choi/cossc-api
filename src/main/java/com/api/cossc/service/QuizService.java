package com.api.cossc.service;

import com.api.cossc.dto.quiz.*;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest);

    QuizCreationResponse create(QuizCreationRequest quizCreationRequest);
    QuizDeletionResponse delete(QuizDeletionRequest quizDeletionRequest);
}
