package com.api.cossc.repository;

import com.api.cossc.util.CookieUtil;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Component
public class CookieAuthorizationRequestRepository implements AuthorizationRequestRepository {
  public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
  public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
  private static final int COOKIE_EXPIRE_SECONDS = 180;

  @Override
  public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
    OAuth2AuthorizationRequest oAuth2AuthorizationRequest = CookieUtil.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
            .map(cookie -> CookieUtil.deserialize(cookie, OAuth2AuthorizationRequest.class))
            .orElse(null);

    assert oAuth2AuthorizationRequest != null;
    if (isNotEmpty(oAuth2AuthorizationRequest.getAttribute("registration_id")) && oAuth2AuthorizationRequest.getAttribute("registration_id").equals("kakao")) {
      // kakao prompt=login 매번 출력
      Map<String, Object> additionalParameters = new HashMap<>(oAuth2AuthorizationRequest.getAdditionalParameters());
      return OAuth2AuthorizationRequest.from(oAuth2AuthorizationRequest)
              .additionalParameters(additionalParameters)
              .authorizationRequestUri(oAuth2AuthorizationRequest.getAuthorizationRequestUri() + "&prompt=login")
              .build();
    }

    return oAuth2AuthorizationRequest;
  }

  @Override
  public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
    if (authorizationRequest == null) {
      CookieUtil.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
      CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
      return;
    }

    CookieUtil.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, CookieUtil.serialize(authorizationRequest), COOKIE_EXPIRE_SECONDS);
    String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);

    if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
      CookieUtil.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, COOKIE_EXPIRE_SECONDS);
    }

  }
//https://kauth.kakao.com/oauth2/authorize?response_type=code&client_id=b5bae11e290d05170b37042bb38d08b5&scope=profile_nickname%20account_email&state=kZrERjXzFlkczFPyTqINJlPAouRnI1lPNtzIvRANgTI%3D&redirect_uri=http://localhost:8080/oauth2/callback/kakao
  @Override
  public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
    return this.loadAuthorizationRequest(request);
  }

  public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
    CookieUtil.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
    CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
  }
}
