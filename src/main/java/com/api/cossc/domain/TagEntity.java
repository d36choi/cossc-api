package com.api.cossc.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TAG")
public class TagEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "tag_id")
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_by", nullable = false)
  private String updatedBy;

  @OneToMany(mappedBy = "tagEntity")
  List<QuizEntity> quizEntities;
}
