package com.main.weather.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "weatherStatus")
public class WeatherStatusEntity {
  @Id private Long id;

  @Column private String main;

  @Column private String description;

  @Column private String weatherStatusIconId;
}
