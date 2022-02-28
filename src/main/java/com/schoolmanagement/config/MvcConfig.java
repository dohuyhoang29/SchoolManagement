package com.schoolmanagement.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    Path userUploadDir = Paths.get("./src/main/resources/static/images/user-images");
    String userUploadPath = userUploadDir.toFile().getAbsolutePath();

    registry.addResourceHandler("/images/user-images/**").addResourceLocations("file:/" + userUploadPath + "/");
  }
}

