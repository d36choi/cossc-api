package com.api.cossc.domain;


import com.api.cossc.service.choice.ChoiceQuestionService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceQuestionEntity extends ChoiceQuestionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "multiple_choice_question_id")
    private Long id;

    @Column(name = "choice_1")
    private String choice1;
    @Column(name = "choice_2")
    private String choice2;
    @Column(name = "choice_3")
    private String choice3;
    @Column(name = "choice_4")
    private String choice4;

    @Column(name = "answer_choice")
    private int correctChoice;

    @Builder
    private MultipleChoiceQuestionEntity(String choice1, String choice2, String choice3, String choice4, int correctChoice) {
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctChoice = correctChoice;
    }

    @Override
    public void add(ChoiceQuestionService choiceQuestionService) {
        choiceQuestionService.save(this);
    }
}
