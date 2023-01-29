package com.api.cossc.dto.quiz;

import com.api.cossc.domain.QuizType;
import lombok.Builder;
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

    private Long tagId;

    @Builder
    private QuizCreationRequest(Long id, String title, String description, QuizType type, Long tagId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.tagId = tagId;
    }

    public static QuizCreationRequest of(Long id, String title, String description, QuizType type, Long tagId) {
        return new QuizCreationRequest(id, title, description, type, tagId);
    }
}
