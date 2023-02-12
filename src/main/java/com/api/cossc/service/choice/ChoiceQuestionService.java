package com.api.cossc.service.choice;

import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.ChoiceQuestionCreationResponse;

public interface ChoiceQuestionService {

    ChoiceQuestionCreationResponse create(ChoiceQuestionCreationRequest choiceQuestionCreationRequest);

}
