package com.schoolmanagement.service;

import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.ResetPasswordRequest;

public interface UserService {
  User getUserByUsername(String username);

  User getUserByEmail(String email);

  User findByUserId(int id);

  void resetPassword(ResetPasswordRequest resetPasswordRequest, Integer id);
}
