package com.api.cossc.service.quiz;

import com.api.cossc.domain.*;
import com.api.cossc.dto.choice.ChoiceQuestionSubmitRequest;
import com.api.cossc.dto.choice.ChoiceQuestionSubmitResponse;
import com.api.cossc.dto.quiz.*;
import com.api.cossc.exception.CommonException;
import com.api.cossc.repository.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.api.cossc.dto.choice.ChoiceQuestionSubmitResponse.ChoiceQuestionResponse;
import static com.api.cossc.dto.choice.ChoiceQuestionSubmitResponse.QuizChoiceAnswerResponse;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final HistoryRepository historyRepository;
    private final TagRepository tagRepository;

    private final DailyQuizRepository dailyQuizRepository;

    private final UserRepository userRepository;

    //TODO:: 퀴즈 풀이시 답변 (ANSWER) 기록 && 퀴즈 채점 후 정답 여부 전달하는 method 구현
    //TODO:: HISTORY 테이블의 역할이 명확히 있는지 확인 필요
    @Transactional
    @Override
    public DailyQuizResponse getDailyQuiz(UserDetails user) {

        UserEntity userEntity = userRepository.findByOauthKeyOrEmail(user.getUsername(), null)
                .orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "유저가 없습니다."));

        List<DailyQuizEntity> dailyQuizEntities = dailyQuizRepository.findAllByDailyQuizId_UserIdAndDailyQuizId_GivenDate(userEntity.getId(), LocalDate.now());

        if (!dailyQuizEntities.isEmpty())
            return DailyQuizResponse.builder()
                    .quizResponses(dailyQuizEntities.stream().map(QuizResponse::of).toList())
                    .build();

        List<QuizEntity> dailyQuiz = makeDailyQuiz(userEntity);


        List<QuizResponse> quizResponses = dailyQuiz.stream().map(QuizResponse::of).toList();

        if (quizResponses.size() < 3) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR, "퀴즈가 부족합니다");
        }


        return DailyQuizResponse.builder()
                .quizResponses(quizResponses)
                .build();
    }

    @NotNull
    @Override
    public List<QuizEntity> makeDailyQuiz(UserEntity userEntity) {
        List<QuizEntity> quizEntities = quizRepository.findAllByTagEntity_Id(userEntity.getTagEntity().getId());
        Collections.shuffle(quizEntities);

        List<Long> allQuizIdByUser = dailyQuizRepository.getAllQuizIdByUser(userEntity.getId());

        List<QuizEntity> dailyQuiz = quizEntities.stream()
                .filter(quiz -> !allQuizIdByUser.contains(quiz.getId()))
                .limit(3)
                .toList();

        dailyQuizRepository.saveAll(dailyQuiz.stream().map(quiz -> DailyQuizEntity.of(quiz, userEntity)).collect(Collectors.toList()));
        return dailyQuiz;
    }

    @Transactional
    @Override
    public QuizCreationResponse create(QuizCreationRequest quizCreationRequest) {

        //퀴즈 생성 이후 ChoiceQuestionService Create 로 생성

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

    @Override
    public boolean isAllSolved(UserEntity userEntity) {
        return dailyQuizRepository.findAllByDailyQuizId_UserIdAndDailyQuizId_GivenDate(userEntity.getId(), LocalDate.now())
                .stream().allMatch(DailyQuizEntity::isSolved);
    }


    @Transactional
    @Override
    public ChoiceQuestionSubmitResponse submitDailyQuiz(UserDetails user, List<ChoiceQuestionSubmitRequest> choiceQuestionSubmitRequests) {

        // user 검증
        UserEntity userEntity = userRepository.findByOauthKeyOrEmail(user.getUsername(), null)
                .orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "유저가 없습니다."));


        // user 의 daily quiz 존재여부 검증
        List<DailyQuizEntity> dailyQuizEntities = dailyQuizRepository.findAllByDailyQuizId_UserIdAndDailyQuizId_GivenDate(userEntity.getId(), LocalDate.now());

        // 없으면, 에러
        if (dailyQuizEntities.isEmpty())
            throw CommonException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("유저의 오늘의 퀴즈가 아직 생성되지 않았습니다.")
                    .build();


        List<QuizChoiceAnswerResponse> choiceAnswerResponses = getQuizChoiceAnswerResponses(choiceQuestionSubmitRequests, dailyQuizEntities);

        List<QuizResponse> quizResponses = dailyQuizEntities.stream().map(QuizResponse::of).toList();

        List<ChoiceQuestionResponse> choiceQuestionResponses = new ArrayList<>();
        IntStream.of(0, choiceAnswerResponses.size())
                .forEach(index -> {
                    choiceQuestionResponses.add(new ChoiceQuestionResponse(quizResponses.get(index), choiceAnswerResponses.get(index)));
                });

        return new ChoiceQuestionSubmitResponse(choiceQuestionResponses);
    }

    @NotNull
    private static List<QuizChoiceAnswerResponse> getQuizChoiceAnswerResponses(List<ChoiceQuestionSubmitRequest> choiceQuestionSubmitRequests, List<DailyQuizEntity> dailyQuizEntities) {
        List<QuizChoiceAnswerResponse> choiceAnswerResponses = new ArrayList<>();
        // 있으면, 각 문제의 정답 가져오기

        // 정답과 제출답변의 일치여부 확인

        // 일치할경우 각 문제의 correct = true
        IntStream.of(0, dailyQuizEntities.size())
                .forEach(index -> {
                    DailyQuizEntity dailyQuizEntity = dailyQuizEntities.get(index);
                    ChoiceQuestionSubmitRequest choiceQuestionSubmitRequest = choiceQuestionSubmitRequests.get(index);

                    if (choiceQuestionSubmitRequest.getQuizType().equals(QuizType.OX)) {
                        OXChoiceQuestionEntity oxChoiceQuestionEntity = dailyQuizEntity.getQuizEntity().getOxChoiceQuestionEntity();
                        dailyQuizEntity.submitCorrect(oxChoiceQuestionEntity.getAnswerChoice() == choiceQuestionSubmitRequest.isMyOXChoiceAnswer());
                        choiceAnswerResponses.add(new QuizChoiceAnswerResponse(String.valueOf(oxChoiceQuestionEntity.getAnswerChoice()), String.valueOf(choiceQuestionSubmitRequest.isMyOXChoiceAnswer())));

                    } else if (choiceQuestionSubmitRequest.getQuizType().equals(QuizType.MULTIPLE_CHOICE)) {
                        MultipleChoiceQuestionEntity multipleChoiceQuestionEntity = dailyQuizEntity.getQuizEntity().getMultipleChoiceQuestionEntity();
                        dailyQuizEntity.submitCorrect(multipleChoiceQuestionEntity.getCorrectChoice() == choiceQuestionSubmitRequest.getMyMultipleChoiceAnswer());
                        choiceAnswerResponses.add(new QuizChoiceAnswerResponse(String.valueOf(multipleChoiceQuestionEntity.getCorrectChoice()), String.valueOf(choiceQuestionSubmitRequest.getMyMultipleChoiceAnswer())));
                    }
                });
        return choiceAnswerResponses;
    }
}
