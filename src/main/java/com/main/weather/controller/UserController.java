package com.main.weather.controller;

import com.main.weather.entity.Role;
import com.main.weather.entity.UserEntity;
import com.main.weather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UserRepository userRepository;

  @GetMapping()
  List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  UserEntity createUser(@RequestBody UserEntity user) {
    String rowPassword = user.getPassword();
    String encode = passwordEncoder.encode(rowPassword);
    user.setPassword(encode);
    user.setRole(Role.ROLE_USER);
    return userRepository.save(user);
  }

  @GetMapping("/{id}")
  UserEntity findOne(@PathVariable Long id) throws Exception {
    // TODO: create exception
    return userRepository.findById(id).orElseThrow(() -> new Exception());
  }

  @PutMapping("/{id}")
  UserEntity userUpdate(@RequestBody UserEntity user, @PathVariable Long id) {
    return userRepository
        .findById(id)
        .map(
            x -> {
              x.setUsername(user.getUsername());
              x.setAddress(user.getAddress());
              x.setRole(user.getRole());
              return userRepository.save(x);
            })
        .orElseGet(() -> userRepository.save(user));
  }

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
  }
}
