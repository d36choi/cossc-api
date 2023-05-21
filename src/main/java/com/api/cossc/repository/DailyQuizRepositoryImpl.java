package com.api.cossc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DailyQuizRepositoryImpl implements DailyQuizRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
//    @Override
//    public UserMainResponse searchUserMain(String oauthKey) {
//        QDailyQuizEntity dailyQuiz = QDailyQuizEntity.dailyQuizEntity;
//        jpaQueryFactory.select()
//                .from(dailyQuiz)
//                .where(dailyQuiz.userEntity.oauthKey.eq(oauthKey))
//                ;
//    }
}
