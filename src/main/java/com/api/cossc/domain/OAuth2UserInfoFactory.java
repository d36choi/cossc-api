package com.api.cossc.domain;

import java.util.Map;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
    return switch (authProvider) {
      case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
      //TODO:: GITHUB user info 구현
      case GITHUB -> null;
      default -> throw new IllegalArgumentException("Invalid Provider Type.");
    };
  }
}
