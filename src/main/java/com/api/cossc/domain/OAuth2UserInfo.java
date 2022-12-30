package com.api.cossc.domain;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public abstract class OAuth2UserInfo {
  protected Map<String, Object> attributes;

  @NotEmpty(message = "user의 key는 empty일 수 없습니다.")
  protected String key;

  public OAuth2UserInfo(Map<String, Object> attributes, String key) {
    this.attributes = attributes;
    this.key = key;
  }

  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public String getKey() {
    return key;
  }

  public abstract String getId();

  public abstract String getName();

  public abstract String getEmail();

  public abstract String getImageUrl();
}
