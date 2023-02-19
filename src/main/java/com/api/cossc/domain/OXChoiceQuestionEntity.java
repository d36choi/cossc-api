package com.api.cossc.domain;


import com.api.cossc.service.choice.ChoiceQuestionService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "OX_CHOICE_QUESTION")
public class OXChoiceQuestionEntity extends ChoiceQuestionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ox_choice_question_id")
    private Long id;

    @Column(name = "answer_choice")
    private Boolean answerChoice;

    @Builder
    private OXChoiceQuestionEntity(Boolean answerChoice) {
        this.answerChoice = answerChoice;
    }

    @Override
    public void add(ChoiceQuestionService choiceQuestionService) {
        choiceQuestionService.save(this);
    }


    @Override
    public void assign(QuizEntity quizEntity) {
        quizEntity.assignChoice(this);
    }
}

