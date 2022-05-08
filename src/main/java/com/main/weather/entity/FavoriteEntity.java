package com.main.weather.entity;

import com.sun.istack.NotNull;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "favorite")
public class FavoriteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column @NotNull private Long seq;

  @Embedded private AddressEmbedded address;

  @NotNull @ManyToOne private UserEntity user;

  @NotNull @OneToOne private CityEntity city;

  @CreationTimestamp private Date createAt;

  @UpdateTimestamp private Date updateAt;

  @Builder
  public FavoriteEntity(Long seq, AddressEmbedded address) {
    this.seq = seq;
    this.address = address;
  }
}
