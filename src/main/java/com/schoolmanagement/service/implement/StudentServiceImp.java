package com.schoolmanagement.service.implement;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
	public void saveStudent(User student) {
		repositories.save(student);
	}

	@Override
	public Iterable<User> getAllStudent() {
		List<User> list = new ArrayList<>();
		for (User user : repositories.findAll()) {
			if (user.hasRole("STUDENT")) {
				list.add(user);
			}
		}

		return list;
	}

	@Override
	public User getStudentById(Integer id) {
		return repositories.findById(id).get();
	}

	@Override
	public Page<User> searchStudent(String fullName, String status, int pageNumber, String sortField,
			String sortDir, String grade, String className, String schoolYear) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByFullNameAndClass(fullName, className, page);
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
	public Page<User> findStudentByClassId(int id , String search , int page) {
		Sort sort = Sort.by("fullName");
		Pageable pageable = PageRequest.of(page -1 , 8 ,sort );
		
		return repositories.findByClassId(id, search,pageable);
	}

	@Override
	public void saveAlLStudent(Iterable<User> studentList) {
		repositories.saveAll(studentList);
	}


	@Override
	public List<User> findAllStudentByClassId(int classid) {
		return repositories.findByIdClass(classid);
	}

	@Override
	public Page<User> findAllStudentByListClass(Set<Class> classList, Integer currentPage, String sortField,
			String sortDir, String fullName, String grade, String className) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(currentPage - 1, 10, sort);
		if (grade.equalsIgnoreCase("")) {
			return repositories.findStudentByListClass(classList, fullName, className, pageable);
		} else {
			return repositories.findStudentByListClassAndGrade(classList, fullName, className, Integer.parseInt(grade), pageable);
		}
	}

	@Override
	public List<User> findAllStudentStudying() {
		Iterable<User> users = repositories.findAll();
		List<User> list = new ArrayList<>();
		for (User student : users) {
			if (student.hasRole("STUDENT")) {
				if (student.getUserInfo().getStatus() != 3) {
					list.add(student);
				}
			}
		}
		return list;
	}

}
