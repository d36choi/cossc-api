package com.api.cossc.controller;

import com.api.cossc.dto.choice.ChoiceQuestionSubmitRequest;
import com.api.cossc.dto.choice.ChoiceQuestionSubmitResponse;
import com.api.cossc.dto.quiz.*;
import com.api.cossc.security.CustomUserDetails;
import com.api.cossc.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class QuizController {

    private final QuizService quizService;


    @GetMapping("/quiz/daily")
    public DailyQuizResponse getDailyQuiz(@AuthenticationPrincipal CustomUserDetails user) {

        return quizService.getDailyQuiz(user);
    }
    @PostMapping("/quiz/create")
    public QuizCreationResponse create(@RequestBody QuizCreationRequest quizCreationRequest) {

        return quizService.create(quizCreationRequest);
    }
    @PatchMapping("/quiz/update")
    public QuizCreationResponse update(@RequestBody QuizCreationRequest quizCreationRequest) {

        return quizService.create(quizCreationRequest);
    }
    @DeleteMapping("/quiz/delete")
    public QuizDeletionResponse delete(@Valid QuizDeletionRequest quizDeletionRequest) {

        return quizService.delete(quizDeletionRequest);
    }

    @PostMapping("/quiz/daily/submit")
    public ChoiceQuestionSubmitResponse test(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody List<ChoiceQuestionSubmitRequest> choiceQuestionSubmitRequests) {

        return quizService.submitDailyQuiz(user, choiceQuestionSubmitRequests);
    }
}
