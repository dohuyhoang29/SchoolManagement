package com.schoolmanagement.service;

import com.schoolmanagement.model.Student;
import com.schoolmanagement.repositories.StudentRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepositories repositories;

	public void saveStudent(Student student) {
		repositories.save(student);
	}

	public Iterable<Student> getAllStudent() {
		return repositories.findAll();
	}

	public Page<Student> getAllStudentByPages(int pageNumber, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		return repositories.findAll(page);
	}

	public Student getStudentById(Integer id) {
		return repositories.findById(id).get();
	}

	public Page<Student> searchStudent(String fullName, String status, int pageNumber, String sortField,
			String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		if (status.equalsIgnoreCase("all")) {
			return repositories.findStudentByFullName(fullName, page);
		} else {
			return repositories.findStudentByFullNameAndStatus(fullName, Integer.parseInt(status), page);
		}
	}
	
	public Page<Student> getAllStudentByClass(int pageNumber) {
		Pageable page = PageRequest.of(pageNumber - 1, 10);

		return repositories.findAll(page);
	}
}
