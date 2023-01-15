package com.api.cossc.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "MULTIPLE_CHOICE_QUESTION")
public class MultipleChoiceQuizEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "multiple_choice_question_id")
    private Long id;

    @Column(name = "choice_1", length = 255)
    private String choice1;
    @Column(name = "choice_2", length = 255)
    private String choice2;
    @Column(name = "choice_3", length = 255)
    private String choice3;
    @Column(name = "choice_4", length = 255)
    private String choice4;

    @Column(name = "answer_choice")
    private int correctChoice;

}
