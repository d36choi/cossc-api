package com.api.cossc.domain;


import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity extends BaseTimeEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;


  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "name",unique = true)
  private String name;

  @Column(name = "oauth_key", unique = true)
  private String oauthKey;

  @Column(name = "description")
  private String description;

  @Column(name = "img")
  private String img;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Enumerated(EnumType.STRING)
  @Column(name = "auth_provider")
  private AuthProvider authProvider;

  @Column(name = "refresh_token")
  private String refreshToken;

  @OneToMany(mappedBy = "userEntity")
  Set<UserQuizEntity> userQuizEntitySet;


  @Builder
  public UserEntity(String email, String name, String oauthKey, String img, UserRole role, AuthProvider authProvider) {
    this.email = email;
    this.name = name;
    this.oauthKey = oauthKey;
    this.img = img;
    this.role = role;
    this.authProvider = authProvider;
  }
}
