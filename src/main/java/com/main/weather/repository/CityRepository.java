package com.main.weather.repository;

import com.main.weather.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {}
