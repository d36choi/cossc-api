package com.api.cossc.controller;

import com.api.cossc.dto.choice.ChoiceQuestionCreationResponse;
import com.api.cossc.dto.choice.MultipleChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.OXChoiceQuestionCreationRequest;
import com.api.cossc.service.choice.ChoiceQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RequiredArgsConstructor
@RestController
public class ChoiceQuestionController {

    private final ChoiceQuestionService choiceQuestionService;

//    @Secured("ROLE_ADMIN")
    @PostMapping("/choice/question/ox")
    public ChoiceQuestionCreationResponse createOX(@RequestBody OXChoiceQuestionCreationRequest choiceQuestionCreationRequest) throws Exception {
        return choiceQuestionService.create(choiceQuestionCreationRequest);
    }
    @PostMapping("/choice/question/multiple")
    public ChoiceQuestionCreationResponse createMultiple(@RequestBody MultipleChoiceQuestionCreationRequest choiceQuestionCreationRequest) throws Exception {
        return choiceQuestionService.create(choiceQuestionCreationRequest);
    }
}
