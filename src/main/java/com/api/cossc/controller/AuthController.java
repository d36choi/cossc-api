package com.api.cossc.controller;

import com.api.cossc.dto.oauth.RefreshTokenInfo;
import com.api.cossc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/refresh")
  public ResponseEntity<RefreshTokenInfo> refreshToken(HttpServletRequest request, HttpServletResponse response, @RequestBody RefreshTokenInfo refreshTokenInfo) {
    return ResponseEntity.ok().body(authService.refreshToken(request, response, refreshTokenInfo));
  }
}
