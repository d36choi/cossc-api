package com.api.cossc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@IdClass(DailyQuizId.class)
@Entity
@Table(name = "DAILY_QUIZ")
public class DailyQuizEntity {

    @Id
    private LocalDate givenDate;

    @Id
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Id
    @ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private QuizEntity quizEntity;

    @Column(name = "solved")
    private boolean solved;

    @Column(name = "correct")
    private boolean correct;
}
