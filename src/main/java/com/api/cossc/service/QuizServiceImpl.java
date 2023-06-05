package com.api.cossc.service;

import com.api.cossc.domain.DailyQuizEntity;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.domain.TagEntity;
import com.api.cossc.domain.UserEntity;
import com.api.cossc.dto.quiz.*;
import com.api.cossc.exception.CommonException;
import com.api.cossc.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final HistoryRepository historyRepository;
    private final TagRepository tagRepository;

    private final DailyQuizRepository dailyQuizRepository;

    private final UserRepository userRepository;


    @Transactional
    @Override
    public DailyQuizResponse getDailyQuiz(DailyQuizRequest dailyQuizRequest) {

        UserEntity userEntity = userRepository.findById(dailyQuizRequest.getUserId())
                .orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "유저가 없습니다."));

        List<DailyQuizEntity> dailyQuizEntities = dailyQuizRepository.findAllByDailyQuizId_UserIdAndDailyQuizId_GivenDate(dailyQuizRequest.getUserId(), LocalDate.now());

        if (!dailyQuizEntities.isEmpty())
            return DailyQuizResponse.builder()
                    .quizResponses(dailyQuizEntities.stream().map(q -> QuizResponse.of(q.getQuizEntity())).toList())
                    .build();

        List<QuizEntity> quizEntities = quizRepository.findAllByTagEntity_Id(dailyQuizRequest.getTagId());
        Collections.shuffle(quizEntities);

        List<Long> allQuizIdByUser = dailyQuizRepository.getAllQuizIdByUser(dailyQuizRequest.getUserId());

        List<QuizEntity> dailyQuiz = quizEntities.stream()
                .filter(quiz -> !allQuizIdByUser.contains(quiz.getId()))
                .limit(3)
                .toList();

        dailyQuizRepository.saveAll(dailyQuiz.stream().map(quiz -> DailyQuizEntity.of(quiz, userEntity)).collect(Collectors.toList()));


        List<QuizResponse> quizResponses = dailyQuiz.stream().map(QuizResponse::of).toList();

        if (quizResponses.size() < 3) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, "퀴즈가 부족합니다");
        }


        return DailyQuizResponse.builder()
                .quizResponses(quizResponses)
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
