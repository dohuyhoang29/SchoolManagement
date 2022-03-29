package com.schoolmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.request.SchoolYearClassRequest;

public interface ClassService {
  void saveClass(Class aClass);

  Class getClassById(Integer id);

  Iterable<Class> getAllClass();

  Page<Class> getAllClassPage(String b , int page);

  Page<Class> getAllByTeacherId(Integer id, int page);

  List<SchoolYearClassRequest> getSchoolYear();

  Class getClassByClassName (String className);

  List<Class> getAllClassInCurrentYear();

  int countAllClass();
}
