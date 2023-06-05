package com.api.cossc.service.quiz;

import com.api.cossc.domain.QuizEntity;
import com.api.cossc.domain.UserEntity;
import com.api.cossc.dto.quiz.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest);

    @NotNull List<QuizEntity> makeDailyQuiz(UserEntity userEntity);

    QuizCreationResponse create(QuizCreationRequest quizCreationRequest);
    QuizDeletionResponse delete(QuizDeletionRequest quizDeletionRequest);
}
