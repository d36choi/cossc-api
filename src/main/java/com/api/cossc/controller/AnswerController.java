package com.api.cossc.controller;

import com.api.cossc.dto.answer.*;
import com.api.cossc.service.answer.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/answer")
    public AnswerResponse getAnswer(final AnswerRequest answerRequest) {

        return answerService.getAnswer(answerRequest);
    }

    @PostMapping("/answer/create")
    public AnswerCreationResponse create(final AnswerCreationRequest answerCreationRequest) {

        return answerService.create(answerCreationRequest);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("/answer/update")
    public AnswerCreationResponse update(final AnswerCreationRequest answerCreationRequest) {

        return answerService.create(answerCreationRequest);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/answer/delete")
    public AnswerDeletionResponse delete(final AnswerDeletionRequest answerDeletionRequest) {

        return answerService.delete(answerDeletionRequest);
    }

}
