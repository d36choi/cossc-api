package com.api.cossc.dto.answer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class AnswerResponse {

    List<AnswerItem> answerItems;

    private AnswerResponse(List<AnswerItem> answerItems) {
        this.answerItems = answerItems;
    }

    public static AnswerResponse of(List<AnswerItem> answerItemList) {
        return new AnswerResponse(answerItemList);
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class AnswerItem {

        private Long id;
        private String text;
        private String createdBy;
        private String updatedBy;
    }
}
