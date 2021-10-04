package com.main.weather.entity;

import com.sun.istack.NotNull;
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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "city")
public class CityEntity {
  @Id private Long id;

  @Column @NotNull private String name;

  @Column @NotNull private String state;

  @Column @NotNull private String country;

  @Column(columnDefinition = "TEXT")
  private String coord;

  @Builder
  public CityEntity(Long id, String name, String state, String country, String coord) {
    this.id = id;
    this.name = name;
    this.state = state;
    this.country = country;
    this.coord = coord;
  }
}
