package com.main.weather.repository;

import com.main.weather.entity.WeatherEnity;
import com.main.weather.entity.WeatherStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStatusRepository extends JpaRepository<WeatherStatusEntity, Long> {}
