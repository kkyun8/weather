package com.main.weather.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.main.weather.auth.PrincipalDetails;
import com.main.weather.entity.UserEntity;
import com.main.weather.repository.UserRepository;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private UserRepository userRepository;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    super(authenticationManager);
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String jwtHeader = request.getHeader("Authorization");
    if (StringUtils.isNullOrEmpty(jwtHeader) || !jwtHeader.startsWith("Bearer")) {
      return;
    }

    String jwtToken = jwtHeader.replace("Bearer ", "");

    String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken)
        .getClaim("username").asString();

    if (username != null) {
      UserEntity user = userRepository.findByUsername(username);
      PrincipalDetails principalDetails = new PrincipalDetails(user);
      // TODO: null
      Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
          null, principalDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    chain.doFilter(request, response);
  }
}

