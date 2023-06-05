package com.api.cossc.controller;

import com.api.cossc.dto.tag.TagRequest;
import com.api.cossc.dto.user.UserMainResponse;
import com.api.cossc.security.CustomUserDetails;
import com.api.cossc.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/user/me")
  public UserMainResponse getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) throws Exception {
    return userService.getUserMe(user);
  }

  @PostMapping("/user/tag")
  public ResponseEntity<Boolean> insertUserTag(@AuthenticationPrincipal CustomUserDetails user, @RequestBody TagRequest tagRequest) throws Exception {
    return ResponseEntity.ok(userService.insertUserTag(user, tagRequest));
  }
}
