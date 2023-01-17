package com.api.cossc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "QUIZ_TAG")
public class QuizTagEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "quiz_tag_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "quiz_id")
  private QuizEntity quizEntity;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private TagEntity tagEntity;


}
