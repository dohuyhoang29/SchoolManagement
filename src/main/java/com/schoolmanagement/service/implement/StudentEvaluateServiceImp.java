package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.StudentEvaluateRepositories;
import com.schoolmanagement.service.StudentEvaluateService;

import java.util.List;

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
	public StudentEvaluate findStudentEvaluateByStudentId(Integer id, Integer semester) {
		return studentEvaluateRepositories.findStudentEvaluateByStudentId(id, semester);
	}

	@Override
	public List<StudentEvaluate> studentEvaluate(int studentId) {
		
		return studentEvaluateRepositories.studentEvaluate(studentId);
	}

  @Override
  public Iterable<StudentEvaluate> getAllByStudent(User student) {
    return studentEvaluateRepositories.findAllByStudent(student);
  }
}
