package com.api.cossc.service;

import com.api.cossc.dto.quiz.DailyQuizRequest;
import com.api.cossc.dto.quiz.DailyQuizResponse;
import com.api.cossc.dto.quiz.QuizCreationRequest;
import com.api.cossc.dto.quiz.QuizCreationResponse;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest);

    QuizCreationResponse create(QuizCreationRequest quizCreationRequest);
}
