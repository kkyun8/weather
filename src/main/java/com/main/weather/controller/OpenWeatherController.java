package com.main.weather.controller;

import com.main.weather.bean.City;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/open-weather")
@RestController
public class OpenWeatherController {

  @Value("${weather-api.url}")
  private String weatherApiUrl;

  @Value("${weather-api.key}")
  private String weatherApiKey;

  @GetMapping("/{city}")
  void getWeatherMap(@PathVariable String city) throws IOException {
    //    https://openweathermap.org/API
    String requestUrl = weatherApiUrl + weatherApiKey + "&q=" + city;

    URL url = new URL(requestUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.connect();

    int responseCode = connection.getResponseCode();

    if (responseCode == HttpURLConnection.HTTP_OK) {

      InputStreamReader isr = new InputStreamReader(connection.getInputStream());

      BufferedReader br = new BufferedReader(isr);

      // TODO: JSON Parser
      System.out.println(br.readLine());

    } else {
      // TODO: fail request
      System.out.println("取得失敗");
    }
  }
}
