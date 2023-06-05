package com.api.cossc.dto.answer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerCreationRequest {
    private String text;
    private String createdBy;
    private String updatedBy;

    @Builder
    public AnswerCreationRequest(String text, String createdBy, String updatedBy) {
        this.text = text;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
