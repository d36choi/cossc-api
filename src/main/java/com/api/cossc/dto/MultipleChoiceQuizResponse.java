package com.api.cossc.dto;

import com.api.cossc.domain.MultipleChoiceQuizEntity;
import com.api.cossc.domain.QuizEntity;

import java.util.Objects;
import java.util.Optional;

public class MultipleChoiceQuizResponse extends QuizResponse {

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int correctChoice;

    public MultipleChoiceQuizResponse(QuizEntity quizEntity) {
        super(QuizResponse.of(quizEntity));

        MultipleChoiceQuizEntity multipleChoiceQuizEntity = Optional.of(quizEntity.getMultipleChoiceQuizEntity())
                .orElseThrow(() -> new IllegalArgumentException("객관식퀴즈가 아닙니다."));

        this.choice1 = multipleChoiceQuizEntity.getChoice1();
        this.choice2 = multipleChoiceQuizEntity.getChoice2();
        this.choice3 = multipleChoiceQuizEntity.getChoice3();
        this.choice4 = multipleChoiceQuizEntity.getChoice4();
        this.correctChoice = multipleChoiceQuizEntity.getCorrectChoice();

    }
}
