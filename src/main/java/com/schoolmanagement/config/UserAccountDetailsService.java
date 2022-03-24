package com.schoolmanagement.config;

import com.schoolmanagement.model.AccountDetails;
import com.schoolmanagement.model.User;
import com.schoolmanagement.service.UserService;
import com.schoolmanagement.service.implement.TeacherServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserAccountDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new AccountDetails(user);
  }
}
