package com.main.weather.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(OpenWeatherController.class)
@TestPropertySource(
    properties = {
      "weather-api.url=https://api.openweathermap.org/data/2.5/weather?APPID=",
      "weather-api.key=8b679c7206615c3fc16b4ebbc3814412"
    })
public class OpenWeatherControllerTests {

  @Autowired MockMvc mockMvc;

  @Test
  public void get_test_ok() throws Exception {
    mockMvc.perform(get("/open-weather/tokyo")).andExpect(status().isOk());
  }

  @Test
  public void get_test_not_found_param() throws Exception {
    mockMvc.perform(get("/open-weather/")).andExpect(status().isOk());
  }

  @Test
  public void get_test_unfaund_city() throws Exception {
    mockMvc.perform(get("/open-weather/a")).andExpect(status().isInternalServerError());
  }
}
