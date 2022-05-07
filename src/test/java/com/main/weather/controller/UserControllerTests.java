package com.main.weather.controller;

import com.main.weather.entity.AddressEmbedded;
import com.main.weather.entity.FavoriteEntity;
import com.main.weather.entity.Role;
import com.main.weather.entity.UserEntity;
import com.main.weather.repository.FavoriteRepository;
import com.main.weather.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

  @Autowired MockMvc mockMvc;
  @Autowired UserController userController;
  @MockBean UserRepository userRepository;

  @Before
  public void setup() {}

  // TODO:
  @Test
  public void find_all() throws Exception {}

  @Test
  public void create() throws Exception {
    AddressEmbedded address = new AddressEmbedded();
    Role role = Role.ROLE_ADMIN;

    UserEntity user = new UserEntity("name", "email", "password", address, role);
    userController.createUser(user);
  }

  @Test
  public void find_one() throws Exception {}

  @Test
  public void update() throws Exception {}

  @Test
  public void delete() throws Exception {}
}
