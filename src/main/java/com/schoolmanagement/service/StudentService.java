package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.User;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;

public interface StudentService {
	void saveStudent(User user);

	Iterable<User> getAllStudent();

	User getStudentById(Integer id);

	Page<User> searchStudent(String fullName, String status, int pageNumber, String sortField,
			String sortDir, String grade, String className, String schoolYear);
	
	Page<User> findStudentByClassId(int id , String search , int page);

	void saveAlLStudent (Iterable<User> studentList);

	List<User> findAllStudentByClassId(int classid);

	Page<User> findAllStudentByListClass(Set<Class> classList, Integer currentPage, String sortField,
			String sortDir, String fullName, String grade, String className);

	List<User> findAllStudentStudying();

	List<User> findAllByAdmissionYear(Integer admissionYear);
}
