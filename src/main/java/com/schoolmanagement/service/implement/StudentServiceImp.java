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
			String sortDir, String grade, String className, String schoolYear) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByFullName(fullName, className, page);
		}
		if (!status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")){
			return repositories.findStudentByFullNameAndStatus(fullName, Integer.parseInt(status), className, page);
		}
		if (status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentBySchoolYearAndGrade(fullName, className, Integer.parseInt(schoolYear), Integer.parseInt(grade), page);
		}
		if (!status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")){
			return repositories.findStudentByGradeAndStatus(fullName, Integer.parseInt(status), Integer.parseInt(grade), className, page);
		}
		if (!status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")){
			return repositories.findStudentBySchoolYearAndStatus(fullName, Integer.parseInt(status), Integer.parseInt(schoolYear), className, page);
		}
		if (status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByGrade(fullName, Integer.parseInt(grade), className, page);
		}
		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentBySchoolYear(fullName, className, Integer.parseInt(schoolYear), page);
		}
		if (!status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudent(fullName, Integer.parseInt(status), Integer.parseInt(grade), className, Integer.parseInt(schoolYear), page);
		}
		return null;
	}

	@Override
	public Page<Student> getAllStudentByClass(int pageNumber) {
		Pageable page = PageRequest.of(pageNumber - 1, 10);

		return repositories.findAll(page);
	}

	@Override
	public Page<Student> findStudentByClassId(int id , String search , int page) {
		Sort sort = Sort.by("fullName");
		Pageable pageable = PageRequest.of(page -1 , 5 ,sort );
		
		return repositories.findByClassId(id, search,pageable);
	}

	@Override
	public void saveAlLStudent(Iterable<Student> studentList) {
		repositories.saveAll(studentList);
	}


}
