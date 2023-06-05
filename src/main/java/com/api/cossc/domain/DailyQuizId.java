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

    @Column(name = "given_date")
    private LocalDate givenDate;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "quiz_id")
    private Long quizId;
}
