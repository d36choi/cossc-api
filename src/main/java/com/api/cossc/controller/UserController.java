package com.api.cossc.controller;

import com.api.cossc.dto.user.UserMainResponse;
import com.api.cossc.security.CustomUserDetails;
import com.api.cossc.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/user/me")
//  @PreAuthorize("hasRole('USER')")
  public UserMainResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) throws Exception {
    return userService.getUserMe(user);
  }
}
