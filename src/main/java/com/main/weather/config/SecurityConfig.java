package com.main.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/swagger-ui/**").antMatchers("/swagger-ui");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable().csrf().disable();
    http.authorizeRequests()
        .antMatchers("/favorite/**")
        .authenticated()
        .antMatchers(HttpMethod.POST, "/users/")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/users")
        .authenticated()
        .antMatchers(HttpMethod.PUT, "/users")
        .authenticated()
        .antMatchers(HttpMethod.DELETE, "/users")
        .authenticated()
        .antMatchers("/city/**")
        .access("hasRole('ROLE_ADMIN')")
        .anyRequest()
        .permitAll();
  }
}
