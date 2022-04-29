package com.main.weather.controller;

import static com.main.weather.entity.QFavoriteEntity.favoriteEntity;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.main.weather.entity.AddressEmbedded;
import com.main.weather.entity.CityEntity;
import com.main.weather.entity.FavoriteEntity;
import com.main.weather.entity.UserEntity;
import com.main.weather.repository.FavoriteQueryRepository;
import com.main.weather.repository.FavoriteRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FavoriteController.class)
public class FavoriteControllerTests {

  @Autowired MockMvc mockMvc;
  @Autowired FavoriteController favoriteController;

  @MockBean FavoriteRepository favoriteRepository;
  @MockBean FavoriteQueryRepository favoriteQueryRepository;

  private AddressEmbedded favoriteAddress =
      new AddressEmbedded("one1", "two1", "city1", "state1", "postalcode1", "country1");

  private FavoriteEntity favorite = new FavoriteEntity(Long.valueOf("1"), favoriteAddress);

  private Long id = Long.valueOf("1");

  @Before
  public void setup() {
    favorite.setId(Long.valueOf("1"));
    favorite.setCreateAt(new Date());
    favorite.setUpdateAt(new Date());

    var city =
        new CityEntity(Long.valueOf("1"), "cityname", "citystate", "citycountry", "citycoord");
    favorite.setCity(city);

    AddressEmbedded userAddress =
        new AddressEmbedded(
            "userone1", "usertwo1", "userity1", "userstate1", "userpostalcode1", "usercountry1");

    var user = new UserEntity("name1", "email1", "password1", userAddress, favorite);
    user.setId(Long.valueOf("1"));
    user.setCreateAt(new Date());
    user.setUpdateAt(new Date());

    favorite.setUser(user);

    List<FavoriteEntity> list = new ArrayList<>();
    list.add(favorite);

    given(favoriteRepository.save(favorite)).willReturn(favorite);
    given(favoriteRepository.findAll()).willReturn(list);
    given(favoriteRepository.findById(id)).willReturn(java.util.Optional.of(favorite));

    given(favoriteQueryRepository.fetchByLimitOffset(anyInt(), anyInt(), anyString(), anyBoolean()))
        .willReturn(list);
  }

  @Test
  public void find_all() throws Exception {
    favoriteController.findAll(0, 5, "createAt", true);
  }

  @Test
  public void create() throws Exception {
    favoriteController.create(favorite);
  }

  @Test
  public void find_one() throws Exception {
    Long id = Long.valueOf("1");
    favoriteController.findOne(id);
  }

  @Test
  public void favoriteUpdate() throws Exception {
    favoriteController.update(favorite, id);
  }

  @Test
  public void favoriteDelete() throws Exception {
    mockMvc.perform(delete("/favorite/1")).andExpect(status().isOk());
  }
}
