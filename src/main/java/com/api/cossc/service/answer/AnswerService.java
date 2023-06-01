package com.api.cossc.service.answer;

import com.api.cossc.dto.answer.*;

public interface AnswerService {
    AnswerResponse getAnswer(AnswerRequest answerRequest);

    AnswerCreationResponse create(AnswerCreationRequest answerCreationRequest);

    AnswerDeletionResponse delete(AnswerDeletionRequest answerDeletionRequest);
}
