package com.main.weather.controller;

import com.main.weather.entity.CityEntity;
import com.main.weather.repository.CityRepository;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/city")
@RestController
public class CityController {

  @Autowired private CityRepository cityRepository;

  @PostMapping("/import")
  void importCityJson(@RequestParam MultipartFile file) throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    Reader reader = new InputStreamReader(file.getInputStream());
    JSONArray jsonArray = (JSONArray) parser.parse(reader);

    if (jsonArray.size() > 0) {
      cityRepository.deleteAll();
    }

    Iterator iterator = jsonArray.iterator();

    while (iterator.hasNext()) {
      JSONObject jsonObject = (JSONObject) iterator.next();

      Long id;
      try {
        id = (Long) jsonObject.get("id");
      } catch (Exception e) {
        double doubleId = (double) jsonObject.get("id");
        id = Math.round(doubleId);
      }

      String name = (String) jsonObject.get("name");
      String state = (String) jsonObject.get("state");
      String country = (String) jsonObject.get("country");
      JSONObject coordJson = (JSONObject) jsonObject.get("coord");
      String coord = coordJson.toJSONString();
      CityEntity city = new CityEntity(id, name, state, country, coord);

      cityRepository.save(city);
    }
  }
}
