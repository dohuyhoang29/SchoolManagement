package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Student;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;

import com.schoolmanagement.model.Student;

public interface StudentService {
	void saveStudent(Student student);

	Iterable<Student> getAllStudent();

	Student getStudentById(Integer id);

	Page<Student> searchStudent(String fullName, String status, int pageNumber, String sortField,
			String sortDir, String grade, String className, String schoolYear);
	
	Page<Student> findStudentByClassId(int id , String search , int page);

	void saveAlLStudent (Iterable<Student> studentList);

	List<Student> findAllStudentByClassId(int classid);

	Page<Student> findAllStudentByListClass(Set<Class> classList, Integer currentPage, String sortField,
			String sortDir, String fullName, String grade, String className);
}
