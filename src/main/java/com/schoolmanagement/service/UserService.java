package com.schoolmanagement.service;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface UserService {
  User saveUser(User user);

  Iterable<User> saveAllUser(Iterable<User> list);

  List<User> getAllUser();

  Page<User> getAllUserByPage(int pageNumber, String sortField, String sortDir);

  Page<User> searchUserByFullName(String fullName, int pageNumber, String sortField,
  String sortDir);

  Page<User> searchUserByFullNameAndDeleted(String fullName, Boolean deleted, int pageNumber,
      String sortField, String sortDir);

  User getUserByUsername(String username);

  User findbyUserid(int id);

  User getUserById(Integer id);

  void makeRetired(Integer id);
  void makeWorking(Integer id);
}
