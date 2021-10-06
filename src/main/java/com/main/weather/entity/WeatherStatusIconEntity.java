package com.main.weather.entity;

import com.sun.istack.NotNull;
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
public class WeatherStatusIconEntity {
  @Id private String id;

  @Column @NotNull private String description;

  @Column @NotNull private String descriptionJp;
}
