package com.api.cossc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "DAILY_QUIZ")
public class DailyQuizEntity {

    @EmbeddedId
    private DailyQuizId dailyQuizId;

//    @Id
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

//    @Id
    @ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private QuizEntity quizEntity;

    @Comment("문제 풀이 여부")
    @Column(name = "solved")
    private boolean solved;

    @Comment("문제 정답 여부")
    @Column(name = "correct")
    private boolean correct;
}
