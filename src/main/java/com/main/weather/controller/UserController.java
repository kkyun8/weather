package com.main.weather.controller;


import com.main.weather.entity.UserEntity;
import com.main.weather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    UserEntity createUser(@RequestBody UserEntity user){
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    UserEntity findOne(@PathVariable Long id) throws Exception {
        // TODO: create exception
        return userRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @PutMapping("/{id}")
    UserEntity userUpdate(@RequestBody UserEntity user, @PathVariable Long id){
        return userRepository.findById(id).map(x -> {
            x.setName(user.getName());
            x.setAddress(user.getAddress());
            return userRepository.save(x);
        }).orElseGet(() -> userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }


}
