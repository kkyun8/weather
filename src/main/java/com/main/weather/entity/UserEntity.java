package com.main.weather.entity;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;

    @Embedded
    @NotNull
    private AddressEmbedded address;

    @OneToMany(mappedBy = "user")
    private Set<FavoriteEntity> favorite = new HashSet<>();

    private void addFavorite(FavoriteEntity favorite) {
        this.favorite.add(favorite);
    }

    @Builder
    public UserEntity(String name, String email, String password, AddressEmbedded address, FavoriteEntity favorite ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.addFavorite(favorite);
    }
}
