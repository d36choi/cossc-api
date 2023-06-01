package com.api.cossc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyQuizId implements Serializable {

    @Column(name = "GIVEN_DATE")
    private LocalDate givenDate;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "QUIZ_ID")
    private Long quizId;
}
