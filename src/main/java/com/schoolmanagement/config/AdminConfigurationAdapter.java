package com.schoolmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {
  public AdminConfigurationAdapter(){}

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
  }
}
