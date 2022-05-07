package com.main.weather.entity;

import com.sun.istack.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "user")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column @NotNull private String username;

  @Column @NotNull private String email;

  @Column @NotNull private String password;

  @Embedded private AddressEmbedded address;

  @OneToMany(mappedBy = "user")
  private Set<FavoriteEntity> favorite = new HashSet<>();

  public void addFavorite(FavoriteEntity favorite) {
    this.favorite.add(favorite);
  }

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column private Date loginDate;

  @CreationTimestamp private Date createAt;

  @UpdateTimestamp private Date updateAt;

  @Builder
  public UserEntity(
      String username, String email, String password, AddressEmbedded address, Role role) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.address = address;
    this.role = role;
  }
}
