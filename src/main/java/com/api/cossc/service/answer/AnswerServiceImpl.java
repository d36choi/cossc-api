package com.api.cossc.service.answer;

import com.api.cossc.converter.answer.AnswerMapper;
import com.api.cossc.domain.AnswerEntity;
import com.api.cossc.dto.answer.*;
import com.api.cossc.repository.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    @Override
    public AnswerResponse getAnswer(AnswerRequest answerRequest) {

        List<AnswerEntity> answers = answerRepository.findAllById(answerRequest.getIds());
        return AnswerResponse.of(answers.stream().map(AnswerMapper.INSTANCE::toAnswerItem).toList());
    }

    @Override
    public AnswerCreationResponse create(AnswerCreationRequest answerCreationRequest) {

        AnswerMapper answerMapper = AnswerMapper.INSTANCE;
        AnswerEntity entity = answerMapper.toAnswerEntity(answerCreationRequest);
        AnswerEntity saved = answerRepository.save(entity);

        return AnswerCreationResponse.of(saved);
    }

    @Override
    public AnswerDeletionResponse delete(AnswerDeletionRequest answerDeletionRequest) {

        answerRepository.delete(AnswerEntity.of(answerDeletionRequest.getId()));
        return new AnswerDeletionResponse(answerDeletionRequest.getId());
    }
}
