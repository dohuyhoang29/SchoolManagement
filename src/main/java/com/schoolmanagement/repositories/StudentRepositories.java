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
	@Query(value = "SELECT u FROM User u WHERE u.userInfo.admissionYear = :admissionYear")
	List<User> findAllByAdmissionYear(@Param("admissionYear") Integer admissionYear);

	@Query(value = "SELECT u FROM User u WHERE u.fullName LIKE %:fullName% AND u.userInfo.aClass.className LIKE %:class%")
	Page<User> findStudentByFullNameAndClass(@Param("fullName") String fullName, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.status = :status AND s.aClass.className LIKE %:class%")
	Page<User> findStudentByFullNameAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.status = :status AND s.aClass.grade = :grade AND s.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear")
	Page<User> findStudent(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("grade") Integer grade, @Param("class") String className, @Param("schoolYear") Integer schoolYear, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.status = :status AND s.aClass.grade = :grade AND s.aClass.className LIKE %:class%")
	Page<User> findStudentByGradeAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("grade") Integer grade, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.status = :status AND s.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear")
	Page<User> findStudentBySchoolYearAndStatus(@Param("fullName") String fullName, @Param("status") Integer status,
			@Param("schoolYear") Integer schoolYear, @Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.aClass.grade = :grade AND s.aClass.className LIKE %:class%")
	Page<User> findStudentByGrade(@Param("fullName") String fullName, @Param("grade") Integer grade,
			@Param("class") String className, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear")
	Page<User> findStudentBySchoolYear(@Param("fullName") String fullName, @Param("class") String className,
			@Param("schoolYear") Integer schoolYear, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.fullName LIKE %:fullName% AND s.userInfo.aClass.className LIKE %:class% AND s.aClass.schoolYear = :schoolYear AND s.aClass.grade = :grade")
	Page<User> findStudentBySchoolYearAndGrade(@Param("fullName") String fullName, @Param("class") String className,
			@Param("schoolYear") Integer schoolYear, @Param("grade") Integer grade, Pageable pageable);

	@Query(value ="SELECT s FROM User s WHERE s.userInfo.aClass.id = (:classId) AND s.fullName LIKE %:fullName%")
	Page<User> findByClassId(@Param("classId") int id, @Param("fullName") String search , Pageable page);
	
	@Query(value ="SELECT s FROM User s WHERE s.userInfo.aClass.id = (:classId)")
	List<User> findByIdClass(@Param("classId") int id);

	@Query(value = "SELECT u FROM User u WHERE u.userInfo.aClass IN :classList AND u.fullName LIKE %:fullName% AND u.userInfo.aClass.className LIKE %:className%")
	Page<User> findStudentByListClass(@Param("classList") Collection<Class> classList,
			@Param("fullName") String fullName, @Param("className") String className, Pageable pageable);

	@Query(value = "SELECT s FROM User s WHERE s.userInfo.aClass IN :classList AND s.fullName LIKE %:fullName% AND s.userInfo.aClass.className LIKE %:className% AND s.aClass.grade = :grade")
	Page<User> findStudentByListClassAndGrade(@Param("classList") Collection<Class> classList,
			@Param("fullName") String fullName, @Param("className") String className, @Param("grade") Integer grade, Pageable pageable);
}
