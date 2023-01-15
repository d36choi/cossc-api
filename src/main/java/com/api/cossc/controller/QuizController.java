package com.api.cossc.controller;

import com.api.cossc.domain.HistoryEntity;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.dto.DailyQuizRequest;
import com.api.cossc.dto.DailyQuizResponse;
import com.api.cossc.dto.QuizResponse;
import com.api.cossc.repository.HistoryRepository;
import com.api.cossc.repository.QuizRepository;
import com.api.cossc.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("quiz")
@RestController
public class QuizController {

    private final QuizService quizService;


    @GetMapping("/daily")
    public DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest) {

        return quizService.getDailyQuiz(dailyQuizRequest);
    }
}
