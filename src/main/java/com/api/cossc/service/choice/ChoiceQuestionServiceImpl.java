package com.api.cossc.service.choice;

import com.api.cossc.domain.ChoiceQuestionEntity;
import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import com.api.cossc.domain.OXChoiceQuestionEntity;
import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.ChoiceQuestionCreationResponse;
import com.api.cossc.repository.OXChoiceQuestionRepository;
import com.api.cossc.service.repository.MultipleChoiceQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {


    private final ChoiceQuestionFactory choiceQuestionFactory;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final OXChoiceQuestionRepository oxChoiceQuestionRepository;

    @Override
    public ChoiceQuestionCreationResponse create(ChoiceQuestionCreationRequest choiceQuestionCreationRequest) {

        try {
            ChoiceQuestionEntity choiceQuestionEntity = choiceQuestionFactory.create(choiceQuestionCreationRequest);
            choiceQuestionEntity.add(this);

        } catch (Exception e){
            System.out.println("e = " + e);
        }


        return null;
    }


    @Override
    public void save(MultipleChoiceQuestionEntity multipleChoiceQuestionEntity) {
        multipleChoiceQuestionRepository.save(multipleChoiceQuestionEntity);
    }

    @Override
    public void save(OXChoiceQuestionEntity oxChoiceQuestionEntity) {
        oxChoiceQuestionRepository.save(oxChoiceQuestionEntity);
    }
}
