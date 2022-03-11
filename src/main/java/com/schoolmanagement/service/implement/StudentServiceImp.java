package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.Student;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepositories repositories;

	@Override
	public void saveStudent(Student student) {
		repositories.save(student);
	}

	@Override
	public Iterable<Student> getAllStudent() {
		return repositories.findAll();
	}

	@Override
	public Page<Student> getAllStudentByPages(int pageNumber, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		return repositories.findAll(page);
	}

	@Override
	public Student getStudentById(Integer id) {
		return repositories.findById(id).get();
	}

	@Override
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

	@Override
	public Page<Student> getAllStudentByClass(int pageNumber) {
		Pageable page = PageRequest.of(pageNumber - 1, 10);

		return repositories.findAll(page);
	}

	@Override
	public Page<Student> findStudentByClassId(int id , String search , int page) {
		Sort sort = Sort.by("fullName");
		Pageable pageable = PageRequest.of(page -1 , 8 ,sort );
		
		return repositories.findByClassId(id, search,pageable);
	}

	@Override
	public List<Student> findAllStudentByClassId(int classid) {
		
		
		return repositories.findByIdClass(classid);
	}

	
}
