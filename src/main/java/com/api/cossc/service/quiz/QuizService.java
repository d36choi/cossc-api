package com.api.cossc.service.quiz;

import com.api.cossc.domain.DailyQuizEntity;
import com.api.cossc.domain.UserEntity;
import com.api.cossc.dto.quiz.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface QuizService {

    DailyQuizResponse getDailyQuiz(UserDetails user);

    @NotNull List<QuizEntity> makeDailyQuiz(UserEntity userEntity);

    QuizCreationResponse create(QuizCreationRequest quizCreationRequest);
    QuizDeletionResponse delete(QuizDeletionRequest quizDeletionRequest);

    boolean isAllSolved(UserEntity userEntity);
}
