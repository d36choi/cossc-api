package com.api.cossc.security;

import static com.api.cossc.dto.oauth.UserRole.USER;

import com.api.cossc.domain.UserEntity;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {
  private final Long id;
  private final String authKey;
  private final Collection<? extends GrantedAuthority> authorities;
  private Map<String, Object> attributes;

  public CustomUserDetails(Long id, String authKey, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.authKey = authKey;
    this.authorities = authorities;
  }

  public static CustomUserDetails create(UserEntity userEntity) {
    List<GrantedAuthority> authorities = Collections.
        singletonList(new SimpleGrantedAuthority(USER.getRole()));

    return new CustomUserDetails(
        userEntity.getId(),
        userEntity.getOauthKey(),
        authorities
    );
  }

  public static CustomUserDetails create(UserEntity userEntity, Map<String, Object> attributes) {
    CustomUserDetails userDetails = CustomUserDetails.create(userEntity);
    userDetails.setAttributes(attributes);
    return userDetails;
  }

  // UserDetail Override
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getUsername() {
    return authKey;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  // OAuth2User Override
  @Override
  public String getName() {
    return String.valueOf(id);
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }
}