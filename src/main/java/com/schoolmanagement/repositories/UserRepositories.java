package com.schoolmanagement.repositories;

import com.schoolmanagement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends PagingAndSortingRepository<User, Integer> {

  Iterable<User> findUserByEmail(String email);

  @Query(value = "SELECT u FROM User u WHERE u.fullName LIKE %:email%")
  Iterable<User> searchUsersByFullName(@Param("email") String search);

  @Query(value = "SELECT u FROM User u WHERE u.email LIKE %:email% AND u.deleted = :deleted")
  Iterable<User> searchUsersByEmailAndDeleted(@Param("email") String search,
      @Param("deleted") Boolean deleted);
    User findById(int integer);
}
