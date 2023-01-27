package com.api.cossc.service;

import com.api.cossc.domain.HistoryEntity;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.dto.quiz.*;
import com.api.cossc.repository.HistoryRepository;
import com.api.cossc.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final HistoryRepository historyRepository;


    @Override
    public DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest) {

        List<QuizEntity> quizEntities = quizRepository.findAllByTagEntity_Id(dailyQuizRequest.getTagId());

        List<HistoryEntity> historyEntities = historyRepository.findAllByUserEntity_Id(dailyQuizRequest.getUserId());

        Set<Long> quizSetSolved = historyEntities.stream()
                .filter(historyEntity -> !historyEntity.getSolved())
                .map(historyEntity -> historyEntity.getQuizEntity().getId())
                .collect(Collectors.toSet());

        List<QuizResponse> quizResponses = new ArrayList<>(quizEntities
                .stream().filter(quiz -> !quizSetSolved.contains(quiz.getId()))
                .map(QuizResponse::of)
                .toList());

        Collections.shuffle(quizResponses);

        return DailyQuizResponse.builder()
                .quizResponses(quizResponses.subList(0,3))
                .build();
    }

    @Override
    public QuizCreationResponse create(QuizCreationRequest quizCreationRequest) {

        QuizEntity quizEntity = quizRepository.findById(quizCreationRequest.getId()).orElse(QuizEntity.emptyOf());

        QuizEntity toBeSaved = quizEntity.of(quizCreationRequest);
        QuizEntity saved = quizRepository.save(toBeSaved);

        return QuizCreationResponse.of(saved);

    }
}