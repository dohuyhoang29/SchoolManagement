package com.schoolmanagement.repositories;

import com.schoolmanagement.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TeacherRepositories extends PagingAndSortingRepository<User, Integer> {

  @Query(value = "SELECT u.* "
      + "FROM user AS u "
      + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
      + "INNER JOIN role AS r ON r.id = ur.role_id "
      + "WHERE ur.role_id = 2 AND u.full_name LIKE %:fullName%", nativeQuery = true)
  List<User> searchUsersByFullName(@Param("fullName") String fullName);

  @Query(value = "SELECT u.* "
      + "FROM user AS u "
      + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
      + "INNER JOIN role AS r ON r.id = ur.role_id "
      + "INNER JOIN user_info AS ui ON u.user_info_id = ui.id "
      + "WHERE ur.role_id = 2 AND u.full_name LIKE %:fullName% AND ui.deleted = :deleted", nativeQuery = true)
  List<User> searchUsersByFullNameAndDeleted(@Param("fullName") String fullName,
      @Param("deleted") Boolean deleted);

  @Query(value = "SELECT COUNT(u.id) "
      + "FROM user AS u "
      + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
      + "INNER JOIN role AS r ON r.id = ur.role_id "
      + "INNER JOIN user_info AS ui ON ui.id = u.user_info_id "
      + "WHERE ur.role_id = 2 AND ui.deleted = 0", nativeQuery = true)
  int countAllTeacher();

  @Modifying
  @Transactional
  @Query(value = "UPDATE User u SET u.userInfo.deleted = :deleted WHERE u.id = :id")
  void changeDeleted(@Param("deleted") Boolean deleted, @Param("id") Integer id);
}
