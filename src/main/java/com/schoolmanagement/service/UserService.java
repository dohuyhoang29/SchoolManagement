package com.schoolmanagement.service;

import com.schoolmanagement.model.Role;
import com.schoolmanagement.model.User;
import com.schoolmanagement.repositories.RoleRepositories;
import com.schoolmanagement.repositories.UserRepositories;
import java.util.ArrayList;
import java.util.List;
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

  @Autowired
  private RoleRepositories roleRepositories;

  public User saveUser(User user) {
    return repo.save(user);
  }

  public Iterable<User> saveAllUser(Iterable<User> list) {
    return repo.saveAll(list);
  }

  public List<User> getAllUser() {
    List<User> list = new ArrayList<>();

    for (User user : repo.findAll()) {
      for (Role role : user.getRoles()) {
        if (role.getRoleID() == 2) {
          list.add(user);
        }
      }
    }

    return list;
  }

  public Page<User> getAllUserByPage(int pageNumber, String sortField, String sortDir) {
    Sort sort = Sort.by(sortField);
    sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
    Pageable page = PageRequest.of(pageNumber - 1, 10, sort);
    return repo.findAll(page);
  }

  public Page<User> searchUserByFullName(String fullName, int pageNumber, String sortField,
      String sortDir) {
    Sort sort = Sort.by(sortField);
    sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
    Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

    return repo.searchUsersByFullName(fullName, page);
  }

  public Page<User> searchUserByFullNameAndDeleted(String fullName, Boolean deleted, int pageNumber,
      String sortField, String sortDir) {
    Sort sort = Sort.by(sortField);
    sort = sortDir.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
    Pageable page = PageRequest.of(pageNumber - 1, 10, sort);

    return repo.searchUsersByFullNameAndDeleted(fullName, deleted, page);
  }

  public User getUserByUsername(String username) {
    return repo.findUserByUsername(username);
  }

  public User findbyUserid(int id) {
    return repo.findById(id);
  }

  public User getUserById(Integer id) {
    return repo.findById(id).get();
  }
}
