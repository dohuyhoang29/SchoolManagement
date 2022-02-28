package com.schoolmanagement.service;

import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepositories repo;

  public User saveUser(User user) {
    return repo.save(user);
  }

  public Iterable<User> getAllUser() {
    return repo.findAll();
  }

  public Page<User> getAllUserByPage(int pageNumber, String sortField, String sortDir) {
    Sort sort = Sort.by(sortField);
    sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
    Pageable page = PageRequest.of(pageNumber - 1, 2, sort);

    return repo.findAll(page);
  }

  public Iterable<User> getUserByEmail(String email) {
    return repo.findUserByEmail(email);
  }

  public User findbyUserid(int id) {
    return repo.findById(id);
  }

  public User getUserById(Integer id) {
    return repo.findById(id).get();
  }

  public Iterable<User> searchUserByFullName(String fullName) {

    return repo.searchUsersByFullName(fullName);
  }

  public Iterable<User> searchUserByFullNameAndDeleted(String fullName, Boolean deleted) {

    return repo.searchUsersByEmailAndDeleted(fullName, deleted);
  }
}
