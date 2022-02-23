package com.schoolmanagement.service;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepositories repo;

  public void saveUser(User user){
    repo.save(user);
  }
}
