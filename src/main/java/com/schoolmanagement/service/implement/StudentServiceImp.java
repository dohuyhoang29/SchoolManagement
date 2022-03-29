package com.schoolmanagement.service.implement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.MarkRequest;
import com.schoolmanagement.model.request.StudentRequest;
import com.schoolmanagement.model.request.response.SelectStudentReponse;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.StudentService;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepositories repositories;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveStudent(StudentRequest studentRequest, MultipartFile multipartFile) {
		try {
			studentRequest.setImage(multipartFile, "upload/image/student_image/");
		} catch (IOException e) {
			System.out.println("Can not found file");
		}

		if (studentRequest.getId() == null) {
			User student = modelMapper.map(studentRequest, User.class);
			int size = repositories.findAllByAdmissionYear(student.getUserInfo().getAdmissionYear()).size();
			student.setUsername("std_" + studentRequest.getUserInfo().getAdmissionYear() + "_" + (size + 1));

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			student.setPassword(encoder.encode("123456"));

			student.setCreatedDate(LocalDateTime.now());
			student.setUpdatedDate(LocalDateTime.now());

			Role role = entityManager.find(Role.class, 4);
			student.addRole(role);

			repositories.save(student);
		} else {
			Optional<User> check = repositories.findById(studentRequest.getId());

			if (check.isPresent()) {
				User student = check.get();
				modelMapper.map(studentRequest, student);
				if (student.getUserInfo().getStatus() == 2) {
					student.getUserInfo().setGraduateYear(LocalDate.now().getYear());
				}
				student.setUpdatedDate(LocalDateTime.now());
				repositories.save(student);
			} else {
				System.out.println("Can not found student");
			}
		}

	}

	@Override
	public Iterable<User> getAllStudent() {
		List<User> list = new ArrayList<>();
		for (User user : repositories.findAllStudent()) {
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
	public Page<User> searchStudent(String fullName, String status, int pageNumber, String sortField, String sortDir,
			String grade, String className, String schoolYear) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByFullNameAndClass(fullName, className, page);
		}
		if (!status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByFullNameAndStatus(fullName, Integer.parseInt(status), className, page);
		}
		if (status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentBySchoolYearAndGrade(fullName, className, Integer.parseInt(schoolYear),
					Integer.parseInt(grade), page);
		}
		if (!status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByGradeAndStatus(fullName, Integer.parseInt(status), Integer.parseInt(grade),
					className, page);
		}
		if (!status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentBySchoolYearAndStatus(fullName, Integer.parseInt(status),
					Integer.parseInt(schoolYear), className, page);
		}
		if (status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentByGrade(fullName, Integer.parseInt(grade), className, page);
		}
		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudentBySchoolYear(fullName, className, Integer.parseInt(schoolYear), page);
		}
		if (!status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			return repositories.findStudent(fullName, Integer.parseInt(status), Integer.parseInt(grade), className,
					Integer.parseInt(schoolYear), page);
		}

		return null;
	}

	@Override
	public Page<User> findStudentByClassId(int id, String search, int page) {
		Sort sort = Sort.by("fullName");
		Pageable pageable = PageRequest.of(page - 1, 8, sort);

		return repositories.findByClassId(id, search, pageable);
	}

	@Override
	public void saveAlLStudent(Iterable<User> studentList) {
		repositories.saveAll(studentList);
	}

	@Override
	public Page<User> findAllStudentByClassId(int classid, int page) {
		Pageable pageable = PageRequest.of(page - 1, 10);

		return repositories.findByIdClass(classid, pageable);
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
			return repositories.findStudentByListClassAndGrade(classList, fullName, className, Integer.parseInt(grade),
					pageable);
		}
	}

	@Override
	public List<User> findAllStudentNotClass() {
		List<User> list = new ArrayList<>();
		for (User user : repositories.getAllStudentNotClass()) {
			if (user.getUserInfo().getAClass() == null) {
				list.add(user);
			}
		}

		return list;
	}

	@Override
	public List<SelectStudentReponse> findAllStudentNotClassByAdmissionYear(int schoolYear, int grade) {
		List<SelectStudentReponse> list = new ArrayList<>();

		for (User user : repositories.getAllStudentNotClassByAdmissionYear(schoolYear, grade)) {
			SelectStudentReponse newItem = new SelectStudentReponse();
			if (user.getUserInfo().getAClass() == null) {
				newItem.setId(user.getId());
				newItem.setText(user.getFullName());

				list.add(newItem);
			}
		}

		return list;
	}

	@Override
	public List<User> findAllByAdmissionYear(Integer admissionYear) {
		return repositories.findAllByAdmissionYear(admissionYear);
	}

	@Override
	public int countAllStudent() {
		return repositories.countAllStudent();
	}

	@Override
	public int countAllNewStudentLastYear() {
		return repositories.countAllNewStudentLastYear();
	}

	@Override
	public List<MarkRequest> findAllStudentHasMark(int type, int semester, int subjectId, int classId) {

		List<MarkRequest> test = new ArrayList<>();
		test = repositories.findAllStudentHasMark(type, semester, subjectId, classId);
		return test;
	}

	@Override
	public User findStudentByUserName(String username) {

		return repositories.findStudentByUsername(username);
	}


}
