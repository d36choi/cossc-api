package com.api.cossc.service.choice;

import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.ChoiceQuestionCreationResponse;
import org.springframework.stereotype.Service;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {


    private ChoiceQuestionFactory choiceQuestionFactory;

    @Override
    public ChoiceQuestionCreationResponse create(ChoiceQuestionCreationRequest choiceQuestionCreationRequest) {
        return null;
    }
}
