package com.api.cossc.dto.answer;

import com.api.cossc.domain.AnswerEntity;
import lombok.Getter;


@Getter
public class AnswerCreationResponse {

    private Long id;

    private AnswerCreationResponse(Long id) {
        this.id = id;
    }

    public static AnswerCreationResponse of(AnswerEntity answerEntity) {
        return new AnswerCreationResponse(answerEntity.getId());
    }
}
