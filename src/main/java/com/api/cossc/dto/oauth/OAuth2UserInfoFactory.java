package com.api.cossc.dto.oauth;

import com.api.cossc.domain.AuthProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, OAuth2User oAuth2User) {
    return switch (authProvider) {
      case GOOGLE -> new GoogleOAuth2UserInfo(oAuth2User.getAttributes(), oAuth2User.getName());
      //TODO:: GITHUB user info 구현
      case GITHUB -> new GithubOAuth2UserInfo(oAuth2User.getAttributes(), oAuth2User.getName());
      case KAKAO -> new KakaoOAuth2UserInfo(oAuth2User.getAttributes(), oAuth2User.getName());
      default -> throw new IllegalArgumentException("Invalid Provider Type.");
    };
  }
}
