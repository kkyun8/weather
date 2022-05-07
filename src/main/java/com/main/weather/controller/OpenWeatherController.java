package com.main.weather.controller;

import com.main.weather.entity.WeatherEnity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
  WeatherEnity getWeatherMap(@PathVariable String city)
      throws IOException, ParseException, Exception {
    //    https://openweathermap.org/API

    String requestUrl = weatherApiUrl + weatherApiKey + "&q=" + city;

    URL url = new URL(requestUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.connect();

    int responseCode = connection.getResponseCode();

    if (responseCode != HttpURLConnection.HTTP_OK) {
      throw new Exception("http not ok code:" + responseCode);
    }

    InputStreamReader isr = new InputStreamReader(connection.getInputStream());

    BufferedReader br = new BufferedReader(isr);

    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(br.readLine());

    JSONObject coordJson = (JSONObject) jsonObject.get("coord");
    String coord = coordJson.toJSONString();
    JSONArray weatherJsonArr = (JSONArray) jsonObject.get("weather");
    String weather = weatherJsonArr.toJSONString();
    String base = (String) jsonObject.get("base");
    JSONObject mainJson = (JSONObject) jsonObject.get("main");
    String main = mainJson.toJSONString();
    Long visibilityLong = (Long) jsonObject.get("visibility");
    int visibility = visibilityLong.intValue();
    JSONObject windJson = (JSONObject) jsonObject.get("wind");
    String wind = windJson.toJSONString();
    JSONObject cloudsJson = (JSONObject) jsonObject.get("clouds");
    String clouds = cloudsJson.toJSONString();
    Long dtLong = (Long) jsonObject.get("dt");
    int dt = dtLong.intValue();
    JSONObject sysJson = (JSONObject) jsonObject.get("sys");
    String sys = sysJson.toJSONString();
    Long timezoneLong = (Long) jsonObject.get("timezone");
    int timezone = timezoneLong.intValue();
    Long cityId = (Long) jsonObject.get("id");

    WeatherEnity weatherEntity =
        new WeatherEnity(
            coord, weather, base, main, visibility, wind, clouds, dt, sys, timezone, cityId);

    return weatherEntity;
  }
}
