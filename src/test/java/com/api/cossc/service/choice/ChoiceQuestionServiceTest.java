package com.api.cossc.service.choice;

import com.api.cossc.ContainerInitialization;
import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import com.api.cossc.domain.OXChoiceQuestionEntity;
import com.api.cossc.domain.QuizType;
import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.ChoiceQuestionCreationResponse;
import com.api.cossc.dto.choice.MultipleChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.OXChoiceQuestionCreationRequest;
import com.api.cossc.repository.OXChoiceQuestionRepository;
import com.api.cossc.service.repository.MultipleChoiceQuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
class ChoiceQuestionServiceTest extends ContainerInitialization {


    @Autowired
    private ChoiceQuestionService choiceQuestionService;

    @Autowired
    private MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    @Autowired
    private OXChoiceQuestionRepository oxChoiceQuestionRepository;

    @Test
    @DisplayName("객관식 답변이 생성되어야한다")
    void create1() throws Exception {

        ChoiceQuestionCreationRequest choiceQuestionCreationRequest =
                new MultipleChoiceQuestionCreationRequest("test1", "test1", "test1", "test1", 0);
        choiceQuestionCreationRequest.setQuizId(1L);
        choiceQuestionCreationRequest.setQuizType(QuizType.MULTIPLE_CHOICE);
        ChoiceQuestionCreationResponse choiceQuestionCreationResponse = choiceQuestionService.create(choiceQuestionCreationRequest);

        MultipleChoiceQuestionEntity multipleChoiceQuestionEntity = multipleChoiceQuestionRepository.findById(1L).orElseThrow(Exception::new);

        Assertions.assertThat(multipleChoiceQuestionEntity.getChoice1()).isEqualTo("test1");

    }
    @Test
    @DisplayName("ox 답변이 생성되어야한다")
    void create2() throws Exception {

        ChoiceQuestionCreationRequest choiceQuestionCreationRequest =
                new OXChoiceQuestionCreationRequest(false);
        choiceQuestionCreationRequest.setQuizId(1L);
        choiceQuestionCreationRequest.setQuizType(QuizType.OX);
        ChoiceQuestionCreationResponse choiceQuestionCreationResponse = choiceQuestionService.create(choiceQuestionCreationRequest);

        OXChoiceQuestionEntity oxChoiceQuestionEntity = oxChoiceQuestionRepository.findById(1L).orElseThrow(Exception::new);

        Assertions.assertThat(oxChoiceQuestionEntity.getAnswerChoice()).isEqualTo(false);

    }
}