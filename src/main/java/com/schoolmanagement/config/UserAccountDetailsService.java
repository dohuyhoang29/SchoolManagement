package com.schoolmanagement.config;

import com.schoolmanagement.model.AdminAndTeacherAccountDetails;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserAccountDetailsService implements UserDetailsService {

  @Autowired
  private UserServiceImp userServiceImp;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userServiceImp.getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new AdminAndTeacherAccountDetails(user);
  }
}
