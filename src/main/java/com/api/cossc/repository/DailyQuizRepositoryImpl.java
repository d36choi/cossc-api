package com.api.cossc.repository;

import com.api.cossc.domain.QDailyQuizEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DailyQuizRepositoryImpl implements DailyQuizRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Long> getAllQuizIdByUser(Long uid) {

        QDailyQuizEntity qDailyQuiz = QDailyQuizEntity.dailyQuizEntity;
        return jpaQueryFactory.select(qDailyQuiz.quizEntity.id)
                .from(qDailyQuiz)
                .where(qDailyQuiz.dailyQuizId.userId.eq(uid))
                .fetch();
    }
}
