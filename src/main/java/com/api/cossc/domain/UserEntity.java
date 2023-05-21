package com.api.cossc.domain;


import com.api.cossc.dto.oauth.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;


  @Column(name = "email")
  private String email;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "oauth_key", unique = true)
  private String oauthKey;

  @Column(name = "description")
  private String description;

  @Column(name = "img")
  private String img;

  @Column(name = "level")
  private String level;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private UserRole role;

  @Enumerated(EnumType.STRING)
  @Column(name = "auth_provider")
  private AuthProvider authProvider;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "updated_by", nullable = false)
  private String updatedBy;

  @Column(name = "correct_count", nullable = false)
  @ColumnDefault("0")
  private int correctCount;

  @Column(name = "solved_count", nullable = false)
  @ColumnDefault("0")
  private int solvedCount;

  @OneToMany(mappedBy = "userEntity")
  Set<UserQuizEntity> userQuizEntitySet;

  @OneToMany(mappedBy = "userEntity")
  List<HistoryEntity> historyEntities;

  @ManyToOne(targetEntity = TagEntity.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_id")
  private TagEntity tagEntity;


  @Builder
  public UserEntity(String email, String name, String oauthKey, String img, String level, UserRole role,
                    AuthProvider authProvider, String createdBy, String updatedBy, int correctCount, int solvedCount) {
    this.email = email;
    this.name = name;
    this.oauthKey = oauthKey;
    this.img = img;
    this.level = level;
    this.role = role;
    this.authProvider = authProvider;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
    this.correctCount = correctCount;
    this.solvedCount = solvedCount;
  }
}
