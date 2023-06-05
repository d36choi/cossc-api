package com.api.cossc.controller;

import com.api.cossc.dto.quiz.*;
import com.api.cossc.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
public class QuizController {

    private final QuizService quizService;


    @GetMapping("/quiz/daily")
    public DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest) {

        return quizService.getDailyQuiz(dailyQuizRequest);
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
}
