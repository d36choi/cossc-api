package com.api.cossc.dto.answer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnswerDeletionRequest {

    private Long id;


    public AnswerDeletionRequest(Long id) {
        this.id = id;
    }
}
