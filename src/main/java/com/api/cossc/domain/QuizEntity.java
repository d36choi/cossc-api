package com.api.cossc.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "QUIZ")
public class QuizEntity extends BaseTimeEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "quiz_id")
  private Long id;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "description", nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_by", nullable = false)
  private String updatedBy;

  @OneToMany(mappedBy = "quizEntity")
  List<UserQuizEntity> userQuizEntitySet;
}
