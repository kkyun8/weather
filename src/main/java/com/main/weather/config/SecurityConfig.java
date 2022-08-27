package com.main.weather.config;

import com.main.weather.filter.JwtAuthenticationFilter;
import com.main.weather.filter.JwtAuthorizationFilter;
import com.main.weather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity // Spring Filter Chainに登録
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserRepository userRepository;

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
    // http.addFilterBefore(new CustomFilter(), SecurityContextPersistenceFilter.class);
    http.httpBasic().disable().csrf().disable();
    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .formLogin()
        .disable()
        .httpBasic()
        .disable()
        .addFilter(new JwtAuthenticationFilter(authenticationManager())) // /loginにフィルタ設定
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
        .authorizeRequests()
        .antMatchers("/favorite/**")
        .authenticated()
        .antMatchers("/users/**")
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers("/city/**")
        .access("hasRole('ROLE_ADMIN')")
        .anyRequest()
        .permitAll();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    //    https://spring.pleiades.io/guides/gs/rest-service-cors/
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowCredentials(true)
            .allowedOriginPatterns("*")
            .allowedHeaders("*")
            .allowedMethods("*");
      }
    };
  }
}
