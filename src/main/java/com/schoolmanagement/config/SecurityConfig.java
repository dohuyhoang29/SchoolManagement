package com.schoolmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private LoginSuccessHandler loginSuccessHandler;

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
        .antMatchers("/css/**", "/js/**", "/images/**", "/plugins/**", "/bundles/**", "/fonts/**" , "/upload/**","/user_css_js/**")
        .permitAll()
        .antMatchers("/insert/**").hasAnyAuthority("ADMIN", "TEACHER", "HOMEROOM_TEACHER")
        .antMatchers("/show/**").hasAnyAuthority("ADMIN", "TEACHER", "HOMEROOM_TEACHER")
        .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "TEACHER", "HOMEROOM_TEACHER")
        .antMatchers("/","/blog/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login")
        .usernameParameter("username")
        .successHandler(loginSuccessHandler)
        .failureUrl("/login?error=true")
        .permitAll()
        .and()
        .csrf().disable()
        .logout()
        .logoutSuccessUrl("/login?logout=true")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .permitAll()
        .and()
        .rememberMe().rememberMeParameter("remember-me-new")
        .and()
        .exceptionHandling().accessDeniedPage("/403");
  }
}
