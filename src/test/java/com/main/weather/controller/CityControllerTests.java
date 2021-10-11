package com.main.weather.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.main.weather.repository.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTests {
  @Autowired MockMvc mockMvc;

  @MockBean CityRepository cityRepository;

  @Test
  public void import_test_ok() throws Exception {
    MockMultipartFile file =
        new MockMultipartFile(
            "file",
            "hello.json",
            MediaType.MULTIPART_FORM_DATA_VALUE,
            "[{\"id\": 833,\"name\": \"Ḩeşār-e Sefīd\",\"state\": \"\",\"country\": \"IR\",\"coord\": {\"lon\": 47.159401,\"lat\": 34.330502}},{\"id\": 2960,\"name\": \"‘Ayn Ḩalāqīm\",\"state\": \"\",\"country\": \"SY\",\"coord\": {\"lon\": 36.321911,\"lat\": 34.940079}}]"
                .getBytes());

    mockMvc.perform(multipart("/city/import").file(file)).andExpect(status().isOk());
  }

  @Test
  public void import_test_empty_param() throws Exception {
    mockMvc.perform(multipart("/city/import")).andExpect(status().isBadRequest());
  }

  @Test
  public void import_test_not_found_http_method() throws Exception {
    mockMvc.perform(get("/city/import")).andExpect(status().isMethodNotAllowed());
  }
}
