package com.schoolmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String uploadPath = System.getProperty("user.dir") + "/src/main/upload/";
    String resource = System.getProperty("user.dir") + "/src/main/resources/static/";
    registry.addResourceHandler("/**", "/upload/**")
        .addResourceLocations("file:" + resource, "file:" + uploadPath);
  }
}

