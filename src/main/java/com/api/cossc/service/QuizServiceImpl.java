package com.api.cossc.service;

import com.api.cossc.domain.HistoryEntity;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.domain.TagEntity;
import com.api.cossc.dto.quiz.*;
import com.api.cossc.exception.CommonException;
import com.api.cossc.repository.HistoryRepository;
import com.api.cossc.repository.QuizRepository;
import com.api.cossc.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final TagRepository tagRepository;


    @Transactional(readOnly = true)
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

        if (quizResponses.size() < 3) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, "퀴즈가 부족합니다");
        }

        Collections.shuffle(quizResponses);

        return DailyQuizResponse.builder()
                .quizResponses(quizResponses.subList(0, 3))
                .build();
    }

    @Transactional
    @Override
    public QuizCreationResponse create(QuizCreationRequest quizCreationRequest) {

        TagEntity tagEntity = tagRepository.findById(quizCreationRequest.getTagId()).orElseThrow(() -> new IllegalArgumentException(""));
        QuizEntity quizEntity;
        if (quizCreationRequest.getId() == null) {
            quizEntity = QuizEntity.emptyOf();
        } else {
            quizEntity = quizRepository.findById(quizCreationRequest.getId()).orElseThrow(IllegalArgumentException::new);
        }

        QuizEntity toBeSaved = quizEntity.of(quizCreationRequest, tagEntity);
        QuizEntity saved = quizRepository.save(toBeSaved);

        return QuizCreationResponse.of(saved);

    }

    @Override
    public QuizDeletionResponse delete(QuizDeletionRequest quizDeletionRequest) {

        quizRepository.deleteById(quizDeletionRequest.getId());

        return QuizDeletionResponse.of(quizDeletionRequest.getId());
    }
}
