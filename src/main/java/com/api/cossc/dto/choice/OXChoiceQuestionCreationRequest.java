package com.api.cossc.dto.choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OXChoiceQuestionCreationRequest extends ChoiceQuestionCreationRequest {

    private Boolean answerChoice;

}
