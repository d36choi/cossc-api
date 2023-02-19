package com.api.cossc.service.choice;

import com.api.cossc.domain.ChoiceQuestionEntity;
import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import com.api.cossc.domain.OXChoiceQuestionEntity;
import com.api.cossc.domain.QuizEntity;
import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.ChoiceQuestionCreationResponse;
import com.api.cossc.repository.OXChoiceQuestionRepository;
import com.api.cossc.repository.QuizRepository;
import com.api.cossc.service.repository.MultipleChoiceQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {


    private final ChoiceQuestionFactory choiceQuestionFactory;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final OXChoiceQuestionRepository oxChoiceQuestionRepository;
    private final QuizRepository quizRepository;

    @Transactional
    @Override
    public ChoiceQuestionCreationResponse create(ChoiceQuestionCreationRequest choiceQuestionCreationRequest) throws Exception {

        QuizEntity quizEntity = quizRepository.findById(choiceQuestionCreationRequest.getQuizId())
                .orElseThrow(() -> new Exception("해당 ID의 퀴즈가 없습니다."));
        if (!quizEntity.getType().equals(choiceQuestionCreationRequest.getQuizType())) {
            throw new Exception("퀴즈와 답안의 타입이 다릅니다.");
        }

        ChoiceQuestionEntity choiceQuestionEntity = choiceQuestionFactory.create(choiceQuestionCreationRequest);
        choiceQuestionEntity.add(this);
        choiceQuestionEntity.assign(quizEntity);

        return new ChoiceQuestionCreationResponse(choiceQuestionEntity.getId());
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
