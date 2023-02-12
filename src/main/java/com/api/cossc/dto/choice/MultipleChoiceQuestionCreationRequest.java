package com.api.cossc.dto.choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultipleChoiceQuestionCreationRequest extends ChoiceQuestionCreationRequest {

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private int correctChoice;


}
