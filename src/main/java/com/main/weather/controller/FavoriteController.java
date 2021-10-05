package com.main.weather.controller;

import com.main.weather.entity.FavoriteEntity;
import com.main.weather.repository.FavoriteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/favorite")
@RestController
public class FavoriteController {

  @Autowired private FavoriteRepository favoriteRepository;

  // TODO: pagination
  @GetMapping()
  List<FavoriteEntity> findAll() {
    return favoriteRepository.findAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  FavoriteEntity create(@RequestBody FavoriteEntity favorite) {
    return favoriteRepository.save(favorite);
  }

  @GetMapping("/{id}")
  FavoriteEntity findOne(@PathVariable Long id) throws Exception {
    return favoriteRepository.findById(id).orElseThrow(() -> new Exception());
  };

  @PutMapping("/{id}")
  FavoriteEntity update(@RequestBody FavoriteEntity favorite, @PathVariable Long id) {
    return favoriteRepository
        .findById(id)
        .map(
            x -> {
              x.setAddress(favorite.getAddress());
              x.setCity(favorite.getCity());
              x.setUser(favorite.getUser());
              return favoriteRepository.save(x);
            })
        .orElseGet(() -> favoriteRepository.save(favorite));
  };

  @DeleteMapping("/{id}")
  void delete(@PathVariable Long id) {
    favoriteRepository.deleteById(id);
  };
}
