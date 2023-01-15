package com.api.cossc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "HISTORY")
public class HistoryEntity extends BaseTimeEntity {

    @Id
    @Column(name = "history_date")
    private LocalDateTime historyDate;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn
    private UserEntity userEntity;

    @ManyToOne(targetEntity = QuizEntity.class, fetch = FetchType.LAZY)
    @JoinColumn
    private QuizEntity quizEntity;


    @Column(name = "solved")
    private Boolean solved;
    @Column(name = "created_by", length = 255)
    private String createdBy;
    @Column(name = "updated_by", length = 255)
    private String updatedBy;
}
