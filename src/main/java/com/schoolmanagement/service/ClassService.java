package com.schoolmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.model.request.SchoolYearClassRequest;

public interface ClassService {
  void saveClass(Class aClass);

  Class getClassById(Integer id);

  Iterable<Class> getAllClass();

  Page<Class> getAllClassPage(String b , int page, String sortField, String sortDir);

  Page<Class> getAllByTeacherId(Integer id, int page, String sortField, String sortDir);

  List<SchoolYearClassRequest> getSchoolYear();

  Class getClassByClassName (String className);

  List<Class> getAllClassInCurrentYear();

	int countAllClass();

	List<MarkRequest> findAverageOneAndTwo (int classId );
}
