package com.api.cossc.dto.choice;

import com.api.cossc.dto.quiz.QuizResponse;

import java.util.List;

public record ChoiceQuestionSubmitResponse(List<ChoiceQuestionResponse> choiceQuestionResponses) {

    public record ChoiceQuestionResponse(QuizResponse quizResponse, QuizChoiceAnswerResponse quizChoiceAnswerResponse) {

    }

    public record QuizChoiceAnswerResponse(String myAnswer, String realAnswer){}
}
