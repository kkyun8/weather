package com.main.weather.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.weather.auth.PrincipalDetails;
import com.main.weather.entity.UserEntity;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// SpringSecurity /loginに自動動作するフィルタ
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) {

    try {
      ObjectMapper om = new ObjectMapper();
      UserEntity user = om.readValue(request.getInputStream(), UserEntity.class);

      UsernamePasswordAuthenticationToken token =
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

      Authentication authentication = authenticationManager.authenticate(token);

      return authentication;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
      throws IOException, ServletException {

    PrincipalDetails principal = (PrincipalDetails) auth.getPrincipal();
    String token = JWT.create().withSubject(principal.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 30)))
        .withClaim("id", principal.getUser().getId())
        .withClaim("username", principal.getUser().getUsername())
        .sign(Algorithm.HMAC512("cos"));

    res.addHeader("Authorization", "Bearer " + token);
  }
}

