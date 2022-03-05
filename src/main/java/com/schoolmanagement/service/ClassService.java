package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.repositories.ClassRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

  @Autowired
  private ClassRepositories classRepositories;

  public void saveClass(Class aClass) {
    classRepositories.save(aClass);
  }

  public Iterable<Class> getAllClass() {
    return classRepositories.findAll();
  }
}
