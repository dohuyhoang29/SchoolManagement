package com.schoolmanagement.service;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.User;
import com.schoolmanagement.model.request.StudentRequest;
import com.schoolmanagement.model.request.response.SelectStudentReponse;
import java.io.IOException;
import com.schoolmanagement.model.request.MarkRequest;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
	void saveStudent(StudentRequest studentRequest, MultipartFile multipartFile) throws IOException;

	Iterable<User> getAllStudent();

	User getStudentById(Integer id);

	Page<User> searchStudent(String fullName, String status, int pageNumber, String sortField,
			String sortDir, String grade, String className, String schoolYear);
	
	Page<User> findStudentByClassId(int id , String search , int page);

	void saveAlLStudent (Iterable<User> studentList);

	Page<User> findAllStudentByClassId(int classid , int page);

	Page<User> findAllStudentByListClass(Set<Class> classList, Integer currentPage, String sortField,
			String sortDir, String fullName, String grade, String className);

	List<User> findAllStudentNotClass();

	List<SelectStudentReponse> findAllStudentNotClassByAdmissionYear(int schoolYear, int grade, Integer id);

	List<User> findAllByAdmissionYear(Integer admissionYear);

	int countAllStudent();

	List<MarkRequest> findAllStudentHasMark(int type , int semester , int subjectId , int classId);

	float getYearPointAverage();

	int countAllNewStudentLastYear();

	int countAllStudentFailLastYear();

	int countAllNewStudentThisYear();

	int countAllStudentGraduateLastYear();

	int countAllStudentAbsentLastYear();

	List<Integer> countAllNewStudentLast10Year();

	List<Integer> countAllStudentFailLast10Year();

	List<Integer> countAllStudentGraduateLast10Year();

	List<Integer> countAllStudentAbsentLast10Year();
	
	List<User> findByClassId(int classId);
}
