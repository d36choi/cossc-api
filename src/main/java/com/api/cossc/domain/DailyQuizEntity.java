package com.api.cossc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "DAILY_QUIZ")
public class DailyQuizEntity {

    @EmbeddedId
    private DailyQuizId dailyQuizId;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    private QuizEntity quizEntity;

    @Comment("문제 풀이 여부")
    @Column(name = "solved")
    private boolean solved;

    @Comment("문제 정답 여부")
    @Column(name = "correct")
    private boolean correct;

    public static DailyQuizEntity of(QuizEntity quizEntity, UserEntity userEntity) {
        DailyQuizEntity dailyQuizEntity = new DailyQuizEntity();
        dailyQuizEntity.setSolved(false);
        dailyQuizEntity.setCorrect(false);
        dailyQuizEntity.setDailyQuizId(DailyQuizId.builder().givenDate(LocalDate.now()).quizId(quizEntity.getId()).userId(userEntity.getId()).build());

        return dailyQuizEntity;
    }

    public void submitCorrect(boolean correct) {
        this.solved = true;
        this.correct = correct;
    }
}
