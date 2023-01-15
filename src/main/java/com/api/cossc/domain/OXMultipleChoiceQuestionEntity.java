package com.api.cossc.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "OX_CHOICE_QUESTION")
public class OXMultipleChoiceQuestionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ox_choice_question_id")
    private Long id;

    @Column(name = "answer_choice")
    private int answerChoice;

}

