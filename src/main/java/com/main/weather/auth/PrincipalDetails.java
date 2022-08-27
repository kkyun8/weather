package com.main.weather.auth;

import com.main.weather.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class PrincipalDetails implements UserDetails {

  // Security -> loginを進む。
  // Security終わったらSecurityのSessionを作成（SecurityContextHolder）
  // SecuritySessionに入る情報はAuthenticationタイプ、Authenticationの中にUserクラスが必要
  // Authentication>>UserDetails
  private UserEntity user;

  public PrincipalDetails(UserEntity user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add((GrantedAuthority) () -> user.getRole().name());
    return collection;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
