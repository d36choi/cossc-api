package com.api.cossc.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@Embeddable
@Builder
public class DailyQuizId implements Serializable {

    @Column(name = "GIVEN_DATE")
    private LocalDate givenDate;
    @Column(name = "USER_ID")
    private Long userEntity;
    @Column(name = "QUIZ_ID")
    private Long quizEntity;
}
