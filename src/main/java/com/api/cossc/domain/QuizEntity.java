package com.api.cossc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "QUIZ")
public class QuizEntity extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quiz_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "quizEntity")
    Set<UserQuizEntity> userQuizEntitySet;
}
