package com.main.weather.repository;

import com.main.weather.entity.WeatherEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEnity, Long> {}
