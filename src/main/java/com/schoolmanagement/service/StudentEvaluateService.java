package com.schoolmanagement.service;

import java.util.List;

import com.schoolmanagement.model.StudentEvaluate;
import com.schoolmanagement.model.User;

public interface StudentEvaluateService {
	void saveStudentEvaluate(StudentEvaluate studentEvaluate);

	StudentEvaluate findStudentEvaluateByStudentId(Integer id, Integer semester);
	
	List<StudentEvaluate> studentEvaluate( int studentId);

  Iterable<StudentEvaluate> getAllByStudent(User student);
}
