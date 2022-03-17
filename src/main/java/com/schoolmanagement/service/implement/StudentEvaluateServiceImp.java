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
  public void saveStudentEvaluate(StudentEvaluate studentEvaluate) {
    studentEvaluateRepositories.save(studentEvaluate);
  }

  @Override
  public StudentEvaluate findStudentEvaluateByStudentId(Integer id) {
    return studentEvaluateRepositories.findStudentEvaluateByStudentId(id);
  }
}
