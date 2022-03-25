package com.schoolmanagement.repositories;

import com.schoolmanagement.model.Class;
import com.schoolmanagement.model.User;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepositories extends PagingAndSortingRepository<User, Integer> {

  @Query(value = "SELECT u "
      + "FROM User u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "WHERE ur.pk.roleId = 4")
  List<User> findAllStudent();

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "WHERE ur.pk.roleId = 4 AND ui.admissionYear = :admissionYear")
  List<User> findAllByAdmissionYear(@Param("admissionYear") Integer admissionYear);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% AND c.className LIKE %:className%")
  Page<User> findStudentByFullNameAndClass(@Param("fullName") String fullName,
      @Param("className") String className, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND u.userInfo.status = :status AND u.aClass.className LIKE %:class%")
  Page<User> findStudentByFullNameAndStatus(@Param("fullName") String fullName,
      @Param("status") Integer status,
      @Param("class") String className, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND u.userInfo.status = :status AND u.aClass.grade = :grade "
      + "AND u.aClass.className LIKE %:class% AND u.aClass.schoolYear = :schoolYear")
  Page<User> findStudent(@Param("fullName") String fullName, @Param("status") Integer status,
      @Param("grade") Integer grade, @Param("class") String className,
      @Param("schoolYear") Integer schoolYear, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND u.userInfo.status = :status AND u.aClass.grade = :grade "
      + "AND u.aClass.className LIKE %:class%")
  Page<User> findStudentByGradeAndStatus(@Param("fullName") String fullName,
      @Param("status") Integer status,
      @Param("grade") Integer grade, @Param("class") String className, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND u.userInfo.status = :status AND u.aClass.className LIKE %:class% "
      + "AND u.aClass.schoolYear = :schoolYear")
  Page<User> findStudentBySchoolYearAndStatus(@Param("fullName") String fullName,
      @Param("status") Integer status,
      @Param("schoolYear") Integer schoolYear, @Param("class") String className, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND c.grade = :grade AND c.className LIKE %:class%")
  Page<User> findStudentByGrade(@Param("fullName") String fullName, @Param("grade") Integer grade,
      @Param("class") String className, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND c.className LIKE %:class% AND c.schoolYear = :schoolYear")
  Page<User> findStudentBySchoolYear(@Param("fullName") String fullName,
      @Param("class") String className,
      @Param("schoolYear") Integer schoolYear, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User AS u "
      + "INNER JOIN UserRole AS ur ON u.id = ur.pk.userId "
      + "INNER JOIN Role AS r ON r.id = ur.pk.roleId "
      + "INNER JOIN UserInfo AS ui ON u.userInfo.id = ui.id "
      + "INNER JOIN Class AS c ON c.id = ui.aClass.id "
      + "WHERE ur.pk.roleId = 4 AND u.fullName LIKE %:fullName% "
      + "AND c.className LIKE %:class% AND c.schoolYear = :schoolYear "
      + "AND c.grade = :grade")
  Page<User> findStudentBySchoolYearAndGrade(@Param("fullName") String fullName,
      @Param("class") String className,
      @Param("schoolYear") Integer schoolYear, @Param("grade") Integer grade, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User u "
      + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
      + "INNER JOIN Role r ON r.id = ur.pk.roleId "
      + "WHERE ur.pk.roleId = 4 AND u.userInfo.aClass.id = (:classId) AND u.fullName LIKE %:fullName%")
  Page<User> findByClassId(@Param("classId") int id, @Param("fullName") String search,
      Pageable page);

  @Query(value = "SELECT u "
      + "FROM User u "
      + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
      + "INNER JOIN Role r ON r.id = ur.pk.roleId "
      + "WHERE ur.pk.roleId = 4 AND u.userInfo.aClass.id = (:classId)")
  List<User> findByIdClass(@Param("classId") int id);

  @Query(value = "SELECT u "
      + "FROM User u "
      + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
      + "INNER JOIN Role r ON r.id = ur.pk.roleId "
      + "WHERE ur.pk.roleId = 4 AND u.userInfo.aClass IN :classList "
      + "AND u.fullName LIKE %:fullName% AND u.userInfo.aClass.className LIKE %:className%")
  Page<User> findStudentByListClass(@Param("classList") Collection<Class> classList,
      @Param("fullName") String fullName, @Param("className") String className, Pageable pageable);

  @Query(value = "SELECT u "
      + "FROM User u "
      + "INNER JOIN UserRole ur ON ur.pk.userId = u.id "
      + "INNER JOIN Role r ON r.id = ur.pk.roleId "
      + "WHERE ur.pk.roleId = 4 AND u.userInfo.aClass IN :classList "
      + "AND u.fullName LIKE %:fullName% AND u.userInfo.aClass.className LIKE %:className% "
      + "AND u.aClass.grade = :grade")
  Page<User> findStudentByListClassAndGrade(@Param("classList") Collection<Class> classList,
      @Param("fullName") String fullName, @Param("className") String className,
      @Param("grade") Integer grade, Pageable pageable);

  @Query(value = "SELECT COUNT(u.id) "
      + "FROM user AS u "
      + "INNER JOIN user_role AS ur ON u.id = ur.user_id "
      + "INNER JOIN role AS r ON r.id = ur.role_id "
      + "INNER JOIN user_info AS ui ON ui.id = u.user_info_id "
      + "WHERE ur.role_id = 4 AND ui.status = 1", nativeQuery = true)
  int countAllStudent();

}
