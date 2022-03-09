package com.schoolmanagement.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  @Bean
  public UserDetailsService userDetailsService() {
    return new UserAccountDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/images/**", "/plugins/**", "/bundles/**", "/fonts/**")
        .permitAll()
        .antMatchers("/insert/**").hasAnyAuthority("ADMIN", "TEACHER")
        .antMatchers("/show/**").hasAnyAuthority("ADMIN", "TEACHER")
        .antMatchers("/edit/**").hasAnyAuthority("ADMIN")
        .anyRequest().permitAll()
        .and()
        .formLogin().loginPage("/admin/login")
        .usernameParameter("username")
        .defaultSuccessUrl("/admin")
        .permitAll()
        .and().csrf().disable()
        .logout().logoutSuccessUrl("/admin/login").permitAll();
  }
}
