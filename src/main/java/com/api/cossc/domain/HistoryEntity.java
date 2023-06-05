package com.api.cossc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 유저의 퀴즈당 문제푼 기록, answer_id 포함
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "HISTORY")
public class HistoryEntity extends BaseTimeEntity {

    @Id
    @Column(name = "history_date")
    private LocalDateTime historyDate;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private QuizEntity quizEntity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "answer_id")
    private AnswerEntity answerEntity;

    @Column(name = "solved")
    private Boolean solved;
    @Column(name = "created_by", length = 255)
    private String createdBy;
    @Column(name = "updated_by", length = 255)
    private String updatedBy;
}
