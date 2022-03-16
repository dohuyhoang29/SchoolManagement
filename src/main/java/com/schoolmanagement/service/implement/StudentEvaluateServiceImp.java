package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.repositories.StudentEvaluateRepositories;
import com.schoolmanagement.service.StudentEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEvaluateServiceImp implements StudentEvaluateService {
  @Autowired
  private StudentEvaluateRepositories studentEvaluateRepositories;


  @Override
  public StudentEvaluate saveStudentEvaluate(StudentEvaluate studentEvaluate) {
    return studentEvaluateRepositories.save(studentEvaluate);
  }
}
