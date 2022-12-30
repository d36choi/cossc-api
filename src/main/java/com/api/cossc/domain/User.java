package com.api.cossc.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseTimeEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true)
  private String name;

  @Column(unique = true)
  private String oauthKey;

  private String description;

  private String img;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @Enumerated(EnumType.STRING)
  private AuthProvider authProvider;

  private String refreshToken;

  @Builder
  public User(String email, String oauthKey, String img, UserRole role, AuthProvider authProvider) {
    this.email = email;
    this.oauthKey = oauthKey;
    this.img = img;
    this.role = role;
    this.authProvider = authProvider;
  }
}
