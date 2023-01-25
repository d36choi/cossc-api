package com.api.cossc.dto.quiz;

import com.api.cossc.domain.QuizType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizCreationRequest {

    private Long id;
    private String title;
    private String description;
    private QuizType type;
    private String createdBy;
    private String updatedBy;
}
