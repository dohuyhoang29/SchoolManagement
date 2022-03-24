package com.schoolmanagement.service;

import com.schoolmanagement.model.request.EditTeacherRequest;
import com.schoolmanagement.model.request.InsertTeacherRequest;
import com.schoolmanagement.model.request.TeacherDetailRequest;
import java.util.List;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
	void saveUser(InsertTeacherRequest user, MultipartFile multipartFile);

	Iterable<User> saveAllUser(Iterable<User> list);

	void updateTeacher(EditTeacherRequest editTeacherRequest, MultipartFile multipartFile);

	List<User> getAllUser();

	Page<User> searchTeacher(int pageNumber, String fullName, String status, String sortField, String sortDir);

	User findByUserId(int id);

	TeacherDetailRequest findTeacherDetail(Integer id);

	EditTeacherRequest getUserById(Integer id);

	void makeRetired(Integer id);

	void makeWorking(Integer id);
	
	int countTeacher();
	
	void deleterole(int roleid , int userid);
}
