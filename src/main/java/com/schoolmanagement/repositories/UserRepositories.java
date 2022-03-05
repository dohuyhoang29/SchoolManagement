package com.schoolmanagement.repositories;

import com.schoolmanagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends PagingAndSortingRepository<User, Integer> {

  Iterable<User> findUserByEmail(String email);

  @Query(value = "SELECT u FROM User u WHERE u.username = :username")
  User findUserByUsername(String username);

  @Query(value = "SELECT u FROM User u WHERE u.fullName LIKE %:fullName%")
  Page<User> searchUsersByFullName(@Param("fullName") String fullName, Pageable pageable);

  @Query(value = "SELECT u FROM User u WHERE u.fullName LIKE %:fullName% AND u.deleted = :deleted")
  Page<User> searchUsersByFullNameAndDeleted(@Param("fullName") String fullName,
      @Param("deleted") Boolean deleted, Pageable pageable);

  User findById(int integer);
}
