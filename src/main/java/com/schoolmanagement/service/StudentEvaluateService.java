package com.schoolmanagement.service;

import com.schoolmanagement.model.StudentEvaluate;

public interface StudentEvaluateService {
  void saveStudentEvaluate(StudentEvaluate studentEvaluate);

  StudentEvaluate findStudentEvaluateByStudentId(Integer id, Integer semester);
}
