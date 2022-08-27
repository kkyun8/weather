package com.main.weather.auth;

import com.main.weather.entity.UserEntity;
import com.main.weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// UserDetailsServiceでAuthentication を作る
// securityのconfigのloginProcessingUrlに設定したURLにリクエストがくると自動にloadUserByUsernameが実行される
// SecuritySession(Authentication(UserDetails))
// FormLoginがenableの場合、/loginが基本URLになってる、disabledなら動作しない
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUsername(username);
    if (user != null) {
      return new PrincipalDetails(user);
    }
    return null;
  }

}
