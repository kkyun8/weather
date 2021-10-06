package com.main.weather.entity;

import com.sun.istack.NotNull;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "weather")
public class WeatherEnity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String coord;

  @Column(columnDefinition = "TEXT")
  private String weather;

  @Column @NotNull private String base;

  @Column(columnDefinition = "TEXT")
  private String main;

  @Column @NotNull private int visibility;

  @Column(columnDefinition = "TEXT")
  private String wind;

  @Column(columnDefinition = "TEXT")
  private String clouds;

  @Column @NotNull private int dt;

  @Column(columnDefinition = "TEXT")
  private String sys;

  @Column @NotNull private int timezone;

  @Column @NotNull private Long cityId;

  @CreationTimestamp private Date createAt;

  @UpdateTimestamp private Date updateAt;

  @Builder
  public WeatherEnity(
      String coord,
      String weather,
      String base,
      String main,
      int visibility,
      String wind,
      String clouds,
      int dt,
      String sys,
      int timezone,
      Long cityId) {
    this.coord = coord;
    this.weather = weather;
    this.base = base;
    this.main = main;
    this.visibility = visibility;
    this.wind = wind;
    this.clouds = clouds;
    this.dt = dt;
    this.sys = sys;
    this.timezone = timezone;
    this.cityId = cityId;
  }
}
