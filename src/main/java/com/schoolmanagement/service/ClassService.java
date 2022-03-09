package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ClassService {
  void saveClass(Class aClass);

  Class getClassById(Integer id);

  Iterable<Class> getAllClass();

  Page<Class> getAllClassPage(String b , int page);
}
