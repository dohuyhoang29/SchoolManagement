package com.schoolmanagement.service;

import com.schoolmanagement.model.ClassTeacherSubject;

public interface ClassTeacherSubjectService {
  void Save(ClassTeacherSubject classTeacherSubject);

  ClassTeacherSubject findByIdOther(int dataU , int dataC);

  Iterable<ClassTeacherSubject> findAllByClassId(int id);

  Iterable<ClassTeacherSubject> findAllTeacherSubject();

  Iterable<ClassTeacherSubject> findAllByTeacher(Integer id);
}
