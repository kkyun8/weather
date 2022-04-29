package com.main.weather.repository;

import static com.main.weather.entity.QFavoriteEntity.favoriteEntity;

import com.main.weather.entity.FavoriteEntity;
import com.main.weather.entity.QFavoriteEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoriteQueryRepository {
  @Autowired EntityManager em;

  public List<FavoriteEntity> fetchByLimitOffset(
      int limit, int offset, String sort, Boolean isAsc) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    QFavoriteEntity f = new QFavoriteEntity("f");
    // TODO: sort
    return queryFactory.select(f).from(f).limit(limit).offset(offset).fetch();
  }
}
