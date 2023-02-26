package com.api.cossc.configuration;

import com.api.cossc.repository.CookieAuthorizationRequestRepository;
import com.api.cossc.security.*;
import com.api.cossc.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Log4j2
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
  private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;


  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/scheme.html").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/oauth2/**", "/auth/**").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated();

    http.cors()                     // CORS on
        .and()
        .csrf().disable()           // CSRF off
        .httpBasic().disable()      // Basic Auth off
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);    // Session off

    http.formLogin().disable()
        .oauth2Login()
        .authorizationEndpoint()
        .baseUri("/oauth2/authorize")
        .authorizationRequestRepository(cookieAuthorizationRequestRepository)
        .and()
        .redirectionEndpoint()
        .baseUri("/oauth2/callback/*")
        .and()
        .userInfoEndpoint()
        .userService(customOAuth2UserService)
        .and()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(authenticationFailureHandler);

    http.exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)	// 401
        .accessDeniedHandler(jwtAccessDeniedHandler);		// 403

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
