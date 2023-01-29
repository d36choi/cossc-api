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
    private QuizCreationRequest(Long id, String title, String description, QuizType type, String createdBy, String updatedBy, Long tagId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.tagId = tagId;
    }

    public static QuizCreationRequest of(Long id, String title, String description, QuizType type, String createdBy, String updatedBy, Long tagId) {
        return new QuizCreationRequest(id, title, description, type, createdBy, updatedBy, tagId);
    }
}
