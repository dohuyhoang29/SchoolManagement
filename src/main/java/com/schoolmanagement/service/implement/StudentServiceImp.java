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
import com.schoolmanagement.repositories.MarkRepositories;
import com.schoolmanagement.repositories.StudentRepositories;
import com.schoolmanagement.service.StudentService;

@Service
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentRepositories studentRepositories;

	@Autowired
	private MarkRepositories markRepositories;

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
			int size = studentRepositories.findAllByAdmissionYear(student.getUserInfo().getAdmissionYear()).size();
			student.setUsername("std_" + studentRequest.getUserInfo().getAdmissionYear() + "_" + (size + 1));

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			student.setPassword(encoder.encode("123456"));

			student.setCreatedDate(LocalDateTime.now());
			student.setUpdatedDate(LocalDateTime.now());

			Role role = entityManager.find(Role.class, 4);
			student.addRole(role);

			studentRepositories.save(student);
		} else {
			Optional<User> check = studentRepositories.findById(studentRequest.getId());

			if (check.isPresent()) {
				User student = check.get();
				modelMapper.map(studentRequest, student);
				if (student.getUserInfo().getStatus() == 2 || student.getUserInfo().getStatus() == 3) {
					student.getUserInfo().setGraduateYear(LocalDate.now().getYear());
				}
				student.setUpdatedDate(LocalDateTime.now());

				studentRepositories.save(student);
			} else {
				System.out.println("Can not found student");
			}
		}

	}

	@Override
	public Iterable<User> getAllStudent() {
		List<User> list = new ArrayList<>();
		
		for (User user : studentRepositories.findAllStudent()) {
			
			if (user.hasRole("STUDENT")) {
				
				list.add(user);
			}
		}

		return list;
	}

	@Override
	public User getStudentById(Integer id) {
		
		return studentRepositories.findById(id).get();
	}

	@Override
	public Page<User> searchStudent(String fullName, String status, int pageNumber, String sortField, String sortDir,
			String grade, String className, String schoolYear) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentByFullNameAndClass(fullName, className, page);
		}
		if (!status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentByFullNameAndStatus(fullName, Integer.parseInt(status), className,
					page);
		}
		if (status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentBySchoolYearAndGrade(fullName, className,
					Integer.parseInt(schoolYear), Integer.parseInt(grade), page);
		}
		if (!status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentByGradeAndStatus(fullName, Integer.parseInt(status),
					Integer.parseInt(grade), className, page);
		}
		if (!status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentBySchoolYearAndStatus(fullName, Integer.parseInt(status),
					Integer.parseInt(schoolYear), className, page);
		}
		if (status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentByGrade(fullName, Integer.parseInt(grade), className, page);
		}
		if (status.equalsIgnoreCase("all") && grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudentBySchoolYear(fullName, className, Integer.parseInt(schoolYear), page);
		}
		if (!status.equalsIgnoreCase("all") && !grade.equalsIgnoreCase("") && !schoolYear.equalsIgnoreCase("")) {
			
			return studentRepositories.findStudent(fullName, Integer.parseInt(status), Integer.parseInt(grade),
					className, Integer.parseInt(schoolYear), page);
		}

		return null;
	}

	@Override
	public Page<User> findStudentByClassId(int id, String search, int page) {
		Sort sort = Sort.by("fullName");
		Pageable pageable = PageRequest.of(page - 1, 8, sort);

		return studentRepositories.findByClassId(id, search, pageable);
	}

	@Override
	public void saveAlLStudent(Iterable<User> studentList) {
		studentRepositories.saveAll(studentList);
	}

	@Override
	public Page<User> findAllStudentByClassId(int classid, int page) {
		Pageable pageable = PageRequest.of(page - 1, 10);

		return studentRepositories.findByIdClass(classid, pageable);
	}

	@Override
	public Page<User> findAllStudentByListClass(Set<Class> classList, Integer currentPage, String sortField,
			String sortDir, String fullName, String grade, String className) {

		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(currentPage - 1, 10, sort);

		if (grade.equalsIgnoreCase("")) {
			return studentRepositories.findStudentByListClass(classList, fullName, className, pageable);
		} else {
			return studentRepositories.findStudentByListClassAndGrade(classList, fullName, className,
					Integer.parseInt(grade), pageable);
		}
	}

	@Override
	public List<User> findAllStudentNotClass() {
		List<User> list = new ArrayList<>();
		for (User user : studentRepositories.getAllStudentNotClass()) {
			if (user.getAClass() == null) {
				list.add(user);
			}
		}

		return list;
	}

	@Override
	public List<SelectStudentReponse> findAllStudentNotClassByAdmissionYear(int schoolYear, int grade, Integer id) {
		List<SelectStudentReponse> list = new ArrayList<>();

		for (User user : studentRepositories.getAllStudentNotClassByAdmissionYear(schoolYear, grade)) {
			SelectStudentReponse newItem = new SelectStudentReponse();
			if (user.getAClass() == null) {

				newItem.setId(user.getId());
				newItem.setText(user.getFullName());

				list.add(newItem);
			}
		}
//    if (id != null) {
//      for (User user: studentRepositories.findAllStudentByClass(id)) {
//        SelectStudentReponse newItem = new SelectStudentReponse();
//
//        newItem.setId(user.getId());
//        newItem.setText(user.getFullName());
//
//        list.add(newItem);
//      }
//    }

		return list;
	}

	@Override
	public List<User> findAllByAdmissionYear(Integer admissionYear) {
		return studentRepositories.findAllByAdmissionYear(admissionYear);
	}

	@Override
	public int countAllStudent() {
		return studentRepositories.countAllStudent();
	}

	@Override
	public int countAllNewStudentLastYear() {
		return studentRepositories.countAllNewStudentLastYear();
	}

	@Override
	public List<MarkRequest> findAllStudentHasMark(int type, int semester, int subjectId, int classId) {

		List<MarkRequest> test = new ArrayList<>();
		test = studentRepositories.findAllStudentHasMark(type, semester, subjectId, classId);
		return test;
	}

	@Override
	public float getYearPointAverage() {
		return 0;
	}

	@Override
	public int countAllStudentFailLastYear() {

		return studentRepositories.countAllStudentFailLastYear();
	}

	@Override
	public int countAllNewStudentThisYear() {
		return studentRepositories.countAllNewStudentThisYear();
	}

	@Override
	public int countAllStudentGraduateLastYear() {
		return studentRepositories.countAllStudentGraduateLastYear();
	}

	@Override
	public int countAllStudentAbsentLastYear() {
		return studentRepositories.countAllStudentAbsentLastYear();
	}

	@Override
	public List<Integer> countAllNewStudentLast10Year() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(studentRepositories.countAllNewStudentByYear(LocalDate.now().getYear() - i));
//      list.add(studentRepositories.countAllNewStudentByYear(2012 - i));
		}

		return list;
	}

	@Override
	public List<Integer> countAllStudentFailLast10Year() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(studentRepositories.countAllStudentFailByYear(LocalDate.now().getYear() - i));
//      list.add(studentRepositories.countAllStudentFailByYear(2012 - i));
		}

		return list;
	}

	@Override
	public List<Integer> countAllStudentGraduateLast10Year() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(studentRepositories.countAllStudentGraduateByYear(LocalDate.now().getYear() - i));
//      list.add(studentRepositories.countAllStudentGraduateByYear(2012 - i));
		}

		return list;
	}

	@Override
	public List<Integer> countAllStudentAbsentLast10Year() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(studentRepositories.countAllStudentAbsentByYear(LocalDate.now().getYear() - i));
//      list.add(studentRepositories.countAllStudentAbsentByYear(2012 - i));
		}

		return list;
	}

	@Override
	public List<User> findByClassId(int classId) {

		return studentRepositories.findByIdClass(classId);
	}

	@Override
	public Page<User> PagingMarkIndex(String fullName, int schoolYear, int grade, int average, int page) {

		Pageable pageable = PageRequest.of(page - 1, 10);

		Page<User> pageUser = studentRepositories.findAllStudentHasClassId(fullName, grade, schoolYear, pageable);

		if (average == 1) {

			pageUser = studentRepositories.findAllStudentHasClassId(fullName, grade, schoolYear, pageable);
		}

		if (average == 2) {

			pageUser = studentRepositories.AllStudentHasClassIdAndMark(fullName, grade, schoolYear, 8, 10, pageable);
		}

		if (average == 3) {

			pageUser = studentRepositories.AllStudentHasClassIdAndMark(fullName, grade, schoolYear, (float) 6.5, 8,
					pageable);
		}

		if (average == 4) {

			pageUser = studentRepositories.AllStudentHasClassIdAndMark(fullName, grade, schoolYear, 5, (float) 6.5,
					pageable);
		}

		if (average == 5) {

			pageUser = studentRepositories.AllStudentHasClassIdAndMark(fullName, grade, schoolYear, 0, 5, pageable);
		}

		if (average == 6) {

			pageUser = studentRepositories.AllStudentHasClassIdAndNoHasMark(fullName, grade, schoolYear, pageable);
		}

		return pageUser;
	}
}
