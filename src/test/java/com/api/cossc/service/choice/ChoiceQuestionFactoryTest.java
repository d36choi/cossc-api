package com.api.cossc.service.choice;

import com.api.cossc.ContainerInitialization;
import com.api.cossc.domain.ChoiceQuestionEntity;
import com.api.cossc.domain.MultipleChoiceQuestionEntity;
import com.api.cossc.domain.QuizType;
import com.api.cossc.dto.choice.ChoiceQuestionCreationRequest;
import com.api.cossc.dto.choice.MultipleChoiceQuestionCreationRequest;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Testcontainers
@Ignore
class ChoiceQuestionFactoryTest extends ContainerInitialization {

    @Autowired
    private ChoiceQuestionFactory choiceQuestionFactory;


    @Test
    @DisplayName("type이 객관식이면, factory를 통해 객관식 정답을 생성해야 한다")
    void test1() throws Exception {

        ChoiceQuestionCreationRequest choiceQuestionCreationRequest =
                new MultipleChoiceQuestionCreationRequest("test1", "test1", "test1", "test1", 0);
        choiceQuestionCreationRequest.setQuizId(1L);
        choiceQuestionCreationRequest.setQuizType(QuizType.MULTIPLE_CHOICE);


        ChoiceQuestionEntity choiceQuestionEntity = choiceQuestionFactory.create(choiceQuestionCreationRequest);

        assertThat(choiceQuestionEntity).isOfAnyClassIn(MultipleChoiceQuestionEntity.class);
    }
}