package com.schoolmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.schoolmanagement.model.User;

public interface UserService {
	User saveUser(User user);

	Iterable<User> saveAllUser(Iterable<User> list);

	List<User> getAllUser();

	Page<User> getAllUserByPage(int pageNumber, String sortField, String sortDir);

	Page<User> searchUserByFullName(String fullName, int pageNumber, String sortField, String sortDir);

	Page<User> searchUserByFullNameAndDeleted(String fullName, Boolean deleted, int pageNumber, String sortField,
			String sortDir);

	User getUserByUsername(String username);

	User getUserByEmail(String email);

	User findByUserId(int id);

	User getUserById(Integer id);

	void makeRetired(Integer id);

	void makeWorking(Integer id);
	
	List<User> findByFullName(String name);
	
	void deleterole(int roleid , int userid);
}
