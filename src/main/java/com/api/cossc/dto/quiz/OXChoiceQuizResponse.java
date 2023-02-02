package com.api.cossc.dto.quiz;

import com.api.cossc.domain.OXChoiceQuestionEntity;
import com.api.cossc.domain.QuizEntity;

import java.util.Optional;

public class OXChoiceQuizResponse extends QuizResponse {

    private Boolean answerChoice;

    public OXChoiceQuizResponse(QuizEntity quizEntity) {
        super(QuizResponse.of(quizEntity));

        OXChoiceQuestionEntity oxChoiceQuestionEntity = Optional.of(quizEntity.getOxChoiceQuestionEntity())
                .orElseThrow(() -> new IllegalArgumentException("OX퀴즈가 아닙니다."));

        this.answerChoice = oxChoiceQuestionEntity.getAnswerChoice();

    }
}
