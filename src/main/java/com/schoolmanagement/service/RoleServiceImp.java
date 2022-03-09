package com.schoolmanagement.service;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.repositories.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{
  @Autowired
  private RoleRepositories repo;

  @Override
  public void saveRole(Role role){
    repo.save(role);
  }
}
