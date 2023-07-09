package com.api.cossc.dto.choice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChoiceQuestionSubmitRequest extends ChoiceQuestionCreationRequest {

    private int myMultipleChoiceAnswer;
    private boolean myOXChoiceAnswer;
}
