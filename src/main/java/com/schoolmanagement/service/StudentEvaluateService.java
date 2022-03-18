package com.schoolmanagement.service;

import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;

public interface StudentEvaluateService {
  void saveStudentEvaluate(StudentEvaluate studentEvaluate);

  StudentEvaluate findStudentEvaluateByStudentId(Integer id, Integer semester);

  Iterable<StudentEvaluate> getAllByStudent(User student);
}
