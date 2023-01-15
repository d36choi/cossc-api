package com.api.cossc.controller;

import com.api.cossc.dto.DailyQuizResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("quiz")
public class QuizController {

    @GetMapping("/daily")
    public DailyQuizResponse getDailyQuiz() {
        return DailyQuizResponse.builder()
                .build();
    }
}
