package com.api.cossc.dto.oauth;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

  public GoogleOAuth2UserInfo(Map<String, Object> attributes, String name) {
    super(attributes, name);
  }

  @Override
  public String getId() {
    return getKey();
  }
  @Override
  public String getName() {
    return (String) attributes.get("name");
  }
  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }
  @Override
  public String getImageUrl() {
    return (String) attributes.get("picture");
  }
}
