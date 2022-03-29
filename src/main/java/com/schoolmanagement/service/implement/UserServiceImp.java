package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import com.schoolmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepositories repo;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public User getUserByUsername(String username) {
    return repo.findUserByUsername(username);
  }

  @Override
  public User getUserByEmail(String email) {
    return repo.findAllByEmail(email);
  }

  @Override
  public User findByUserId(int id) {
    return repo.findById(id);
  }
}
