package com.schoolmanagement.service;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Class;

public interface ClassService {
  void saveClass(Class aClass);

  Class getClassById(Integer id);

  Iterable<Class> getAllClass();

  Page<Class> getAllClassPage(String b , int page);
}
