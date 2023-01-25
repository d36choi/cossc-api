package com.api.cossc.controller;

import com.api.cossc.dto.quiz.DailyQuizRequest;
import com.api.cossc.dto.quiz.DailyQuizResponse;
import com.api.cossc.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuizController {

    private final QuizService quizService;


    @GetMapping("/quiz/daily")
    public DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest) {

        return quizService.getDailyQuiz(dailyQuizRequest);
    }
    @PostMapping("/quiz/create")
    public DailyQuizResponse create(DailyQuizRequest dailyQuizRequest) {

        return null;
    }
    @PatchMapping("/quiz/update")
    public DailyQuizResponse update(DailyQuizRequest dailyQuizRequest) {

        return null;
    }
    @DeleteMapping("/quiz/delete")
    public DailyQuizResponse delete(DailyQuizRequest dailyQuizRequest) {

        return null;
    }
}
