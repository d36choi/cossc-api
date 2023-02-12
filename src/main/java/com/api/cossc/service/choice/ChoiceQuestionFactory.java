package com.api.cossc.service.choice;

import com.api.cossc.converter.answer.MultipleChoiceMapper;
import com.api.cossc.domain.ChoiceQuestionEntity;
import com.api.cossc.domain.QuizType;
import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.MultipleChoiceQuestionCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChoiceQuestionFactory {

    public ChoiceQuestionEntity create(ChoiceQuestionCreationRequest choiceQuestionCreationRequest) throws Exception {

        ChoiceQuestionEntity choiceQuestionEntity;

        if (QuizType.MULTIPLE_CHOICE.equals(choiceQuestionCreationRequest.getQuizType())) {
            choiceQuestionEntity = MultipleChoiceMapper.INSTANCE.toMultipleChoiceQuestionEntity((MultipleChoiceQuestionCreationRequest) choiceQuestionCreationRequest);
        } else {
            throw new Exception("why");
        }

        return choiceQuestionEntity;
    }
}
