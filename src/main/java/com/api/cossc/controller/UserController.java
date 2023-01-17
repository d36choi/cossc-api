package com.api.cossc.controller;

import com.api.cossc.domain.UserEntity;
import com.api.cossc.repository.UserRepository;
import com.api.cossc.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  @GetMapping("/me")
  @PreAuthorize("hasRole('USER')")
  public UserEntity getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
    return userRepository.findById(user.getId()).orElseThrow(() -> new IllegalStateException("not found user"));
  }
}
