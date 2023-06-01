package com.api.cossc.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 답변 기록 (history에서 분리)
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ANSWER")
public class AnswerEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "answer_id")
  private Long id;

  @Column(name = "text", nullable = false, columnDefinition = "TEXT")
  private String text;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_by", nullable = false)
  private String updatedBy;

  @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;


  @Builder
  private AnswerEntity(String text, String createdBy, String updatedBy) {
    this.text = text;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
  }

  private AnswerEntity(Long id) {
    this.id = id;
  }

  public static AnswerEntity of(Long id) {
    return new AnswerEntity(id);
  }
}
