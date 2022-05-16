package com.main.weather.config;

import com.main.weather.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  //  @Bean TODO: Filter設定するとswaggerが見えない
  //  public FilterRegistrationBean<CustomFilter> firstFilter() {
  //    FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>(new
  // CustomFilter());
  //    bean.addUrlPatterns("/*");
  //    bean.setOrder(0); // フィルタの順番設定
  //    return bean;
  //  }
}
