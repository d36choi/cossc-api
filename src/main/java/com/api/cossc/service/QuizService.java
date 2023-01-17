package com.api.cossc.service;

import com.api.cossc.dto.DailyQuizRequest;
import com.api.cossc.dto.DailyQuizResponse;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest);
}
