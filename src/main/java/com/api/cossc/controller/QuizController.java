package com.api.cossc.controller;

import com.api.cossc.dto.DailyQuizResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("quiz")
public class QuizController {

    @GetMapping("/daily")
    public DailyQuizResponse getDailyQuiz() {
        // user ID
        // type 조회
        // quiz 에서 type
        // history filtering

        return DailyQuizResponse.builder()
                .build();
    }
}
