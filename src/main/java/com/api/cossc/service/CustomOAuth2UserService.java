package com.api.cossc.service;

import com.api.cossc.domain.AuthProvider;
import com.api.cossc.domain.UserEntity;
import com.api.cossc.dto.oauth.OAuth2UserInfo;
import com.api.cossc.dto.oauth.OAuth2UserInfoFactory;
import com.api.cossc.dto.oauth.UserRole;
import com.api.cossc.exception.OAuthProcessingException;
import com.api.cossc.repository.UserRepository;
import com.api.cossc.security.CustomUserDetails;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

  // OAuth2UserRequest에 있는 Access Token으로 유저정보 get
  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    return process(oAuth2UserRequest, oAuth2User);
  }

  // 획득한 유저정보를 Java Model과 맵핑하고 프로세스 진행
  private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
    AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
    OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User);

    String oauthKey = userInfo.getKey();

    Optional<UserEntity> userOptional = userRepository.findByOauthKeyOrEmail(oauthKey, userInfo.getEmail());
    UserEntity userEntity;

    if (userOptional.isPresent()) {
      userEntity = userOptional.get();
      if (authProvider != userEntity.getAuthProvider()) {
        throw new OAuthProcessingException("Wrong Match Auth Provider");
      }

    } else {
      userEntity = createUser(userInfo, oauthKey, authProvider);
    }
    return CustomUserDetails.create(userEntity, oAuth2User.getAttributes());
  }

  private UserEntity createUser(OAuth2UserInfo userInfo, String oauthKey, AuthProvider authProvider) {

    String name = Optional.of(userInfo.getName()).orElse(RandomStringUtils.random(10, true, true));

    UserEntity userEntity = UserEntity.builder()
        .email(userInfo.getEmail())
        .img(userInfo.getImageUrl())
        .name(name)
        .role(UserRole.USER)
        .oauthKey(oauthKey)
        .authProvider(authProvider)
        .createdBy(name)
        .updatedBy(name)
        .build();

    return userRepository.save(userEntity);
  }
}
