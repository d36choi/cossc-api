package com.api.cossc.domain;

import java.util.List;
import javax.persistence.*;

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

  @Column(name = "type")
  @Convert(converter = QuizTypeConverter.class)
  private QuizType type;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_by", nullable = false)
  private String updatedBy;

  @OneToMany(mappedBy = "quizEntity")
  List<UserQuizEntity> userQuizEntitySet;

  @OneToOne
  @JoinColumn(name = "multiple_choice_question_id")
  private MultipleChoiceQuizEntity multipleChoiceQuizEntity;

  @OneToOne
  @JoinColumn(name = "ox_choice_question_id")
  private OXChoiceQuestionEntity oxChoiceQuestionEntity;

  @OneToMany(mappedBy = "quizEntity")
  List<HistoryEntity> historyEntities;

  @ManyToOne(targetEntity = TagEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_id")
  private TagEntity tagEntity;
}
