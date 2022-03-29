package com.schoolmanagement.service;

import com.schoolmanagement.model.User;

public interface UserService {
  User getUserByUsername(String username);

  User getUserByEmail(String email);

  User findByUserId(int id);
}
