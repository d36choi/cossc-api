package com.api.cossc.controller;

import com.api.cossc.domain.HistoryEntity;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.dto.DailyQuizRequest;
import com.api.cossc.dto.DailyQuizResponse;
import com.api.cossc.dto.QuizResponse;
import com.api.cossc.repository.HistoryRepository;
import com.api.cossc.repository.QuizRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("quiz")
@RestController
public class QuizController {

    private final QuizRepository quizRepository;
    private final HistoryRepository historyRepository;

    public QuizController(QuizRepository quizRepository, HistoryRepository historyRepository) {
        this.quizRepository = quizRepository;
        this.historyRepository = historyRepository;
    }

    @GetMapping("/daily")
    public DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest) {

        Optional<List<QuizEntity>> quizEntities = quizRepository.findAllByTagEntity_Id(dailyQuizRequest.getTagId());

        Optional<List<HistoryEntity>> historyEntities = historyRepository.findAllByUserEntity_Id(dailyQuizRequest.getUserId());

        Set<Long> quizSetSolved = historyEntities.orElse(Collections.emptyList())
                .stream().map(historyEntity -> historyEntity.getQuizEntity().getId())
                .collect(Collectors.toSet());

        List<QuizResponse> quizResponses = new ArrayList<>(quizEntities.orElse(Collections.emptyList())
                .stream().filter(quiz -> !quizSetSolved.contains(quiz.getId()))
                .map(QuizResponse::of)
                .toList());

        Collections.shuffle(quizResponses);

        return DailyQuizResponse.builder()
                .quizResponses(quizResponses.subList(0,3))
                .build();
    }
}
