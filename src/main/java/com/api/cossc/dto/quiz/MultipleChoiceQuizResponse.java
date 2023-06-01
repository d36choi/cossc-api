package com.api.cossc.dto.quiz;

import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import com.api.cossc.domain.QuizEntity;

import java.util.Optional;

public class MultipleChoiceQuizResponse extends QuizResponse {

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int correctChoice;

    public MultipleChoiceQuizResponse(QuizEntity quizEntity) {
        super(QuizResponse.of(quizEntity));

        MultipleChoiceQuestionEntity multipleChoiceQuestionEntity = Optional.of(quizEntity.getMultipleChoiceQuestionEntity())
                .orElseThrow(() -> new IllegalArgumentException("객관식퀴즈가 아닙니다."));

        this.choice1 = multipleChoiceQuestionEntity.getChoice1();
        this.choice2 = multipleChoiceQuestionEntity.getChoice2();
        this.choice3 = multipleChoiceQuestionEntity.getChoice3();
        this.choice4 = multipleChoiceQuestionEntity.getChoice4();
        this.correctChoice = multipleChoiceQuestionEntity.getCorrectChoice();

    }
}
