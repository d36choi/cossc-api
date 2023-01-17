package com.api.cossc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
}
