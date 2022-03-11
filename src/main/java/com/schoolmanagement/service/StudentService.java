package com.schoolmanagement.service;

import com.schoolmanagement.model.Student;
import java.util.List;
import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Student;

public interface StudentService {
	void saveStudent(Student student);

	Iterable<Student> getAllStudent();

	Page<Student> getAllStudentByPages(int pageNumber, String sortField, String sortDir);

	Student getStudentById(Integer id);

	Page<Student> searchStudent(String fullName, String status, int pageNumber, String sortField,
			String sortDir, String grade, String className, String schoolYear);

	Page<Student> getAllStudentByClass(int pageNumber);
	
	Page<Student> findStudentByClassId(int id , String search , int page);

	Page<Student> searchStudentByClass(Integer id);

	void saveAlLStudent (Iterable<Student> studentList);

	List<Student> findAllStudentByClassId(int classid);
}
