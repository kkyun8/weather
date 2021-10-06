package com.main.weather.repository;

import com.main.weather.entity.WeatherStatusIconEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStatusIconRepository
    extends JpaRepository<WeatherStatusIconEntity, String> {}
