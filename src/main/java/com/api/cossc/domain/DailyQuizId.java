package com.api.cossc.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DailyQuizId implements Serializable {

    @Column(name = "given_date")
    private LocalDate givenDate;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "quiz_id")
    private Long quizId;
}
