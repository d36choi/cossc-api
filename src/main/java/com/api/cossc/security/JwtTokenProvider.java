package com.api.cossc.security;

import com.api.cossc.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class JwtTokenProvider {

  private final String SECRET_KEY;
  private final String COOKIE_REFRESH_TOKEN_KEY;
  private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60;		// 1hour
  private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 7;	// 1week
  private final String AUTHORITIES_KEY = "role";
  private final String ISSUER = "cossc";

  private final UserRepository userRepository;

  public JwtTokenProvider(@Value("${app.auth.token.secret-key}") String secretKey,
                          @Value("${app.auth.token.refresh-cookie-key}") String cookieKey,
                          UserRepository userRepository) {

    this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
    this.COOKIE_REFRESH_TOKEN_KEY = cookieKey;
    this.userRepository = userRepository;
  }

  public String createAccessToken(Authentication authentication) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH);

    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    String userId = user.getName();
    String role = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

    return Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .setSubject(userId)
        .claim(AUTHORITIES_KEY, role)
        .setIssuer(ISSUER)
        .setIssuedAt(now)
        .setExpiration(validity)
        .setAudience(user.getUsername())
        .compact();
  }

  public void createRefreshToken(Authentication authentication, HttpServletResponse response) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH);

    String refreshToken = Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
        .setIssuer(ISSUER)
        .setIssuedAt(now)
        .setExpiration(validity)
        .compact();

    saveRefreshToken(authentication, refreshToken);

    ResponseCookie cookie = ResponseCookie.from(COOKIE_REFRESH_TOKEN_KEY, refreshToken)
        .httpOnly(true)
        .secure(true)
        .sameSite("Lax")
        .maxAge(REFRESH_TOKEN_EXPIRE_LENGTH/1000)
        .path("/")
        .build();

    response.addHeader("Set-Cookie", cookie.toString());
  }

  private void saveRefreshToken(Authentication authentication, String refreshToken) {
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
    Long id = Long.valueOf(user.getName());

    userRepository.updateRefreshToken(id, refreshToken);
  }

  // Access Token을 검사하고 얻은 정보로 Authentication 객체 생성
  public Authentication getAuthentication(String accessToken) {
    Claims claims = parseClaims(accessToken);

    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    CustomUserDetails principal = new CustomUserDetails(Long.valueOf(claims.getSubject()), claims.getAudience(), authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }

  public Boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.info("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalStateException e) {
      log.info("JWT 토큰이 잘못되었습니다");
    }
    return false;
  }

  // Access Token 만료시 갱신때 사용할 정보를 얻기 위해 Claim 리턴
  private Claims parseClaims(String accessToken) {
    try {
      return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }
}