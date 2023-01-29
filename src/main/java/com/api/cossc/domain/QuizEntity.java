package com.api.cossc.domain;

import com.api.cossc.dto.quiz.QuizCreationRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


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

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "multiple_choice_question_id")
  private MultipleChoiceQuizEntity multipleChoiceQuizEntity;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "ox_choice_question_id")
  private OXChoiceQuestionEntity oxChoiceQuestionEntity;

  @OneToMany(mappedBy = "quizEntity")
  List<HistoryEntity> historyEntities;

  @ManyToOne(targetEntity = TagEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_id")
  private TagEntity tagEntity;


  public static QuizEntity emptyOf() {
    return new QuizEntity();
  }

  public QuizEntity of(QuizCreationRequest quizCreationRequest, TagEntity tagEntity) {
    this.id = quizCreationRequest.getId();
    this.title = quizCreationRequest.getTitle();
    this.description = quizCreationRequest.getDescription();
    this.type = quizCreationRequest.getType();
    this.createdBy = "test";
    this.updatedBy = "test";
    this.tagEntity = tagEntity;
    return this;
  }
}
