package com.api.cossc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

  @OneToMany(mappedBy = "tagEntity")
  List<QuizEntity> quizEntities;


  public void setId(Long id) {
    this.id = id;
  }

  private TagEntity(Long id) {
    this.id = id;
  }

  private TagEntity(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static TagEntity of(Long id, String name) {
    return new TagEntity(id, name);
  }

}
