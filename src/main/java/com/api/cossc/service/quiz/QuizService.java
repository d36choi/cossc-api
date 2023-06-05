package com.api.cossc.service.quiz;

import com.api.cossc.dto.quiz.*;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest);

    QuizCreationResponse create(QuizCreationRequest quizCreationRequest);
    QuizDeletionResponse delete(QuizDeletionRequest quizDeletionRequest);
}
