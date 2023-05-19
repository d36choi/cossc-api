package com.api.cossc.service;

import com.api.cossc.dto.oauth.RefreshTokenInfo;
import com.api.cossc.exception.CommonException;
import com.api.cossc.repository.UserRepository;
import com.api.cossc.security.CustomUserDetails;
import com.api.cossc.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

  @Value("${app.auth.token.refresh-cookie-key}")
  private String cookieKey;

  private final UserRepository userRepository;
  private final JwtTokenProvider tokenProvider;

  public String refreshToken(HttpServletRequest request, HttpServletResponse response, RefreshTokenInfo refreshTokenInfo) {
    // 1. Validation Refresh Token

    if (!tokenProvider.validateToken(refreshTokenInfo.refreshToken())) {
      throw new CommonException(HttpStatus.BAD_REQUEST, "Not Validated Refresh Token");
    }

    // 2. 유저정보 얻기
    Authentication authentication = tokenProvider.getAuthentication(refreshTokenInfo.accessToken());
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    Long id = Long.valueOf(user.getName());

    // 3. Match Refresh Token
    String savedToken = userRepository.getRefreshTokenById(id);

    if (!savedToken.equals(refreshTokenInfo.refreshToken())) {
      throw new CommonException(HttpStatus.BAD_REQUEST, "Not Matched Refresh Token");
    }

    // 4. JWT 갱신
    String accessToken = tokenProvider.createAccessToken(authentication);
    tokenProvider.createRefreshToken(authentication, response);

    return accessToken;
  }
}